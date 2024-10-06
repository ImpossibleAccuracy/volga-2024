package com.simp.service.timetable.domain.service;

import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Appointment;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.DateRange;
import com.simp.service.shared.domain.model.Timetable;
import com.simp.service.shared.domain.service.AppointmentService;
import com.simp.service.timetable.data.model.AppointmentEntity;
import com.simp.service.timetable.data.repository.AppointmentRepository;
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
public class AppointmentServiceImpl implements AppointmentService {
    public static final int TICKET_LENGTH_MINUTES = 30;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Mono<? extends Appointment> create(Caller caller, Timetable timetable, Instant time) {
        // TODO: check rights
        return Mono
                .just(AppointmentEntity.builder()
                        .time(time)
                        .timetable(timetable.id())
                        .build())
                .flatMap(appointmentRepository::save);
    }

    @Override
    public Mono<? extends Appointment> get(Caller caller, long id) {
        // TODO: check rights
        return appointmentRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Appointment not found")));
    }

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
        // TODO: check rights
        return appointmentRepository.deleteSoft(appointment.id());
    }

    private static List<DateRange> generateTimetableTickets(Instant from, Instant to) {
        var duration = Duration.between(from, to);

        var totalMinutes = Double.valueOf(duration.toMinutes());
        var ticketsCount = (int) Math.floor(totalMinutes / TICKET_LENGTH_MINUTES);

        var result = new ArrayList<DateRange>(ticketsCount);

        var itemStart = from;
        for (int i = 0; i < ticketsCount; i++) {
            var itemEnd = itemStart.plus((long) i * TICKET_LENGTH_MINUTES, ChronoUnit.MINUTES);

            result.add(new DateRange(itemStart, itemEnd));

            itemStart = itemEnd;
        }

        return result;
    }
}
