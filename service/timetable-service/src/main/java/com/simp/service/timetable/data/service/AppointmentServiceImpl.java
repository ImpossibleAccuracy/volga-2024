package com.simp.service.timetable.data.service;

import com.simp.service.shared.domain.exception.InvalidArgumentsException;
import com.simp.service.shared.domain.exception.OperationDeniedException;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Appointment;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.DateRange;
import com.simp.service.shared.domain.model.Timetable;
import com.simp.service.shared.domain.security.UserRole;
import com.simp.service.timetable.data.model.AppointmentEntity;
import com.simp.service.timetable.data.repository.AppointmentRepository;
import com.simp.service.timetable.domain.service.LocalAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements LocalAppointmentService {
    public static final int TICKET_LENGTH_MINUTES = 30;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Mono<? extends Appointment> create(Caller caller, Timetable timetable, Instant time) {
        var startTime = time.minus(TICKET_LENGTH_MINUTES, ChronoUnit.MINUTES).plus(1, ChronoUnit.MILLIS);
        var endTime = time.plus(TICKET_LENGTH_MINUTES, ChronoUnit.MINUTES).minus(1, ChronoUnit.MILLIS);

        if (time.isBefore(timetable.from()) || endTime.isAfter(timetable.to())) {
            throw new InvalidArgumentsException("Time out of timetable bounds");
        }

        return appointmentRepository
                .existsByTimeBetweenAndTimetableAndDeletedFalse(
                        startTime,
                        endTime,
                        timetable.id()
                )
                .handle((exists, sync) -> {
                    if (exists) {
                        throw new InvalidArgumentsException("There is another appointment on this time");
                    } else {
                        sync.next(false);
                    }
                })
                .map(a -> AppointmentEntity.builder()
                        .time(time)
                        .timetable(timetable.id())
                        .build())
                .flatMap(appointmentRepository::save);
    }

    @Override
    public Mono<? extends Appointment> get(Caller caller, long id) {
        return appointmentRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Appointment not found")))
                .handle((a, sync) -> {
                    if (a.creator() != caller.account().id() && !caller.hasRole(UserRole.MANAGER)) {
                        throw new OperationDeniedException("No such permissions");
                    } else {
                        sync.next(a);
                    }
                });
    }

    @Override
    public Mono<Boolean> existsByTimetable(Timetable timetable) {
        return appointmentRepository.existsByTimetableAndDeletedFalse(timetable.id());
    }

    @Override
    public Flux<DateRange> buildFreeAppointments(Caller caller, Timetable timetable) {
        var ticketsList = generateTimetableTickets(timetable.from(), timetable.to());

        return appointmentRepository
                .findByTimetableAndDeletedFalse(timetable.id())
                .collectList()
                .zipWith(Mono.just(ticketsList))
                .map(tuple -> {
                    var occupied = tuple.getT1();
                    var tickets = tuple.getT2();

                    return tickets.stream()
                            .filter(t -> occupied
                                    .stream()
                                    .noneMatch(t2 -> t.isInstantIn(t2.time())))
                            .toList();
                })
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Void> delete(Caller caller, Appointment appointment) {
        if (appointment.creator() != caller.account().id() && caller.hasRole(UserRole.MANAGER)) {
            throw new OperationDeniedException("No such permissions");
        }

        return appointmentRepository.deleteSoft(appointment.id());
    }

    private static List<DateRange> generateTimetableTickets(Instant from, Instant to) {
        var duration = Duration.between(from, to);

        var totalMinutes = Double.valueOf(duration.toMinutes());
        var ticketsCount = (int) Math.floor(totalMinutes / TICKET_LENGTH_MINUTES);

        var result = new ArrayList<DateRange>(ticketsCount);

        var itemStart = from;
        for (int i = 0; i < ticketsCount; i++) {
            var itemEnd = itemStart.plus(TICKET_LENGTH_MINUTES, ChronoUnit.MINUTES);

            result.add(new DateRange(itemStart, itemEnd));

            itemStart = itemEnd;
        }

        return result;
    }
}
