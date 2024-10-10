package com.simp.service.timetable.data.service;

import com.simp.service.shared.domain.exception.InvalidArgumentsException;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.*;
import com.simp.service.shared.domain.security.UserRole;
import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.timetable.data.model.TimetableEntity;
import com.simp.service.timetable.data.repository.TimetableRepository;
import com.simp.service.timetable.domain.service.LocalAppointmentService;
import com.simp.service.timetable.domain.service.LocalTimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements LocalTimetableService {
    private static final int MAX_APPOINTMENT_DURATION_HOURS = 12;

    private final TimetableRepository timetableRepository;
    private final LocalAppointmentService appointmentService;
    private final DoctorService doctorService;

    @Override
    public Mono<? extends Timetable> create(Caller caller, Hospital hospital, Account doctor, Instant from, Instant to, Room room) {
        caller.requireRole(UserRole.MANAGER);

        checkAppointmentDateRange(from, to);

        return Mono.just(TimetableEntity.builder()
                        .hospital(hospital.id())
                        .doctor(doctor.id())
                        .from(from)
                        .to(to)
                        .room(room.id())
                        .build())
                .flatMap(timetableRepository::save);
    }

    @Override
    public Mono<? extends Timetable> get(Caller caller, long id) {
        return timetableRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Timetable not found")));
    }

    @Override
    public Flux<? extends Timetable> getByHospital(Caller caller, Hospital hospital, Pagination<Instant> pagination) {
        return timetableRepository.findByHospitalAndDateRange(hospital.id(), pagination.from(), pagination.count());
    }

    @Override
    public Flux<? extends Timetable> getByDoctor(Caller caller, Account doctor, Pagination<Instant> pagination) {
        return timetableRepository.findByDoctorAndDateRange(doctor.id(), pagination.from(), pagination.count());
    }

    @Override
    public Flux<? extends Timetable> getByRoom(Caller caller, Room room, Pagination<Instant> pagination) {
        caller.requireRole(UserRole.DOCTOR, UserRole.MANAGER);
        return timetableRepository.findByRoomAndDateRange(room.id(), pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Timetable> update(Caller caller, Timetable target, Hospital hospital, Account doctor, Instant from, Instant to, Room room) {
        caller.requireRole(UserRole.MANAGER);

        checkAppointmentDateRange(from, to);

        var entity = (TimetableEntity) target;

        return appointmentService
                .existsByTimetable(target)
                .handle((exists, sync) -> {
                    if (exists) {
                        sync.error(new InvalidArgumentsException("Timetable cannot be changed if there are an appointment"));
                    } else {
                        sync.next(false);
                    }
                })
                .map(w -> (entity.toBuilder()
                        .hospital(hospital.id())
                        .doctor(doctor.id())
                        .from(from)
                        .to(to)
                        .room(room.id())
                        .build()))
                .flatMap(timetableRepository::save);
    }

    @Override
    public Mono<Void> deleteByHospital(Caller caller, Hospital hospital) {
        caller.requireRole(UserRole.MANAGER);
        return timetableRepository.deleteSoftByHospital(hospital.id());
    }

    @Override
    public Mono<Void> deleteByDoctor(Caller caller, Account doctor) {
        caller.requireRole(UserRole.MANAGER);
        return timetableRepository.deleteSoftByDoctor(doctor.id());
    }

    @Override
    public Mono<Void> delete(Caller caller, Timetable target) {
        caller.requireRole(UserRole.MANAGER);
        return timetableRepository.deleteSoft(target.id());
    }

    private static void checkAppointmentDateRange(Instant from, Instant to) {
        if (Duration.between(from, to).toHours() > MAX_APPOINTMENT_DURATION_HOURS) {
            throw new InvalidArgumentsException("Appointment duration cannot exceed than %d hours".formatted(MAX_APPOINTMENT_DURATION_HOURS));
        }

        var fromZoned = from.atZone(ZoneOffset.UTC);

        if (fromZoned.getMinute() % 30 != 0) {
            throw new InvalidArgumentsException("Start time must be a multiple of 30 minutes.");
        }

        var toZoned = to.atZone(ZoneOffset.UTC);

        if (toZoned.getMinute() % 30 != 0) {
            throw new InvalidArgumentsException("End time must be a multiple of 30 minutes.");
        }

        if (fromZoned.getSecond() != 0 || toZoned.getSecond() != 0) {
            throw new InvalidArgumentsException("No seconds allowed!");
        }
    }
}
