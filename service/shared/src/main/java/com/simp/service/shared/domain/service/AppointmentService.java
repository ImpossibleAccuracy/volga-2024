package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Appointment;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.DateRange;
import com.simp.service.shared.domain.model.Timetable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface AppointmentService {
    Mono<? extends Appointment> create(Caller caller, Timetable timetable, Instant time);

    Mono<? extends Appointment> get(Caller caller, long id);

    Flux<DateRange> buildFreeAppointments(Caller caller, Timetable timetable);

    Mono<Void> delete(Caller caller, Appointment appointment);
}
