package com.simp.service.timetable.domain.service;

import com.simp.service.shared.domain.exception.InvalidArgumentsException;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.*;
import com.simp.service.shared.domain.service.TimetableService;
import com.simp.service.timetable.data.model.TimetableEntity;
import com.simp.service.timetable.data.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoField;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    private static final int MAX_APPOINTMENT_DURATION_HOURS = 12;

    private final TimetableRepository timetableRepository;
    private final AppointmentServiceImpl appointmentService; // TODO

    @Override
    public Mono<? extends Timetable> create(Caller caller, Hospital hospital, Account doctor, Instant from, Instant to, Room room) {
        checkAppointmentDateRange(from, to);

        // TODO: check admin
        return Mono.just(TimetableEntity.builder()
                        .hospital(hospital.id())
                        .doctor(doctor.id()) // TODO: check doctor
                        .from(from) // TODO: check time range
                        .to(to)
                        .room(room.id())
                        .build())
                .flatMap(timetableRepository::save);
    }

    @Override
    public Mono<? extends Timetable> get(Caller caller, long id) {
        // TODO: check admin
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
        // TODO: check admin
        return timetableRepository.findByRoomAndDateRange(room.id(), pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Timetable> update(Caller caller, Timetable target, Hospital hospital, Account doctor, Instant from, Instant to, Room room) {
        checkAppointmentDateRange(from, to);

        // TODO: check admin
        return appointmentService
                .existsByTimetable(target)
                .handle((exists, sync) -> {
                    if (exists) {
                        sync.error(new InvalidArgumentsException("Timetable cannot be changed if there are an appointment"));
                    } else {
                        sync.next(false);
                    }
                })
                .map(w -> (TimetableEntity.builder()
                        .id(target.id())
                        .hospital(hospital.id())
                        .doctor(doctor.id()) // TODO: check doctor
                        .from(from) // TODO: check time range
                        .to(to)
                        .room(room.id())
                        .build()))
                .flatMap(timetableRepository::save);
    }

    @Override
    public Mono<Void> deleteByHospital(Caller caller, Hospital hospital) {
        // TODO: check admin
        return timetableRepository.deleteSoftByHospital(hospital.id());
    }

    @Override
    public Mono<Void> deleteByDoctor(Caller caller, Account doctor) {
        // TODO: check admin
        return timetableRepository.deleteSoftByDoctor(doctor.id());
    }

    @Override
    public Mono<Void> delete(Caller caller, Timetable target) {
        // TODO: check admin
        return timetableRepository.deleteSoft(target.id());
    }

    private static void checkAppointmentDateRange(Instant from, Instant to) {
        if (Duration.between(from, to).toHours() > MAX_APPOINTMENT_DURATION_HOURS) {
            throw new InvalidArgumentsException("Appointment duration cannot exceed than %d hours".formatted(MAX_APPOINTMENT_DURATION_HOURS));
        }

        if (from.get(ChronoField.MINUTE_OF_HOUR) % 30 != 0) {
            throw new InvalidArgumentsException("Start time must be a multiple of 30 minutes.");
        }

        if (to.get(ChronoField.MINUTE_OF_HOUR) % 30 != 0) {
            throw new InvalidArgumentsException("End time must be a multiple of 30 minutes.");
        }

        if (from.get(ChronoField.SECOND_OF_MINUTE) != 0 || to.get(ChronoField.SECOND_OF_MINUTE) != 0) {
            throw new InvalidArgumentsException("No seconds allowed! 🤡"); // TODO
        }
    }
}
