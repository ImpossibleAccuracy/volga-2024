package com.simp.service.timetable.domain.service;

import com.simp.service.shared.domain.model.Appointment;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Timetable;
import com.simp.service.shared.domain.service.AppointmentService;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface LocalAppointmentService extends AppointmentService {
    Mono<? extends Appointment> create(Caller caller, Timetable timetable, Instant time);

    Mono<Boolean> existsByTimetable(Timetable timetable);

    Mono<Void> delete(Caller caller, Appointment appointment);
}
