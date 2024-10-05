package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Appointment;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Timetable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface AppointmentService {
    Mono<Appointment> create(Caller caller, Timetable timetable, Instant time);

    Mono<Appointment> get(Caller caller, long id);

    Flux<Appointment> getByTimetable(Caller caller, Timetable timetable);

    Mono<Void> delete(Caller caller, Appointment appointment);
}
