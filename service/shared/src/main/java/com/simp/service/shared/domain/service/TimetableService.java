package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface TimetableService {
    Mono<? extends Timetable> create(
            Caller caller,
            Hospital hospital,
            Account doctor,
            Instant from,
            Instant to,
            Room room);

    Mono<? extends Timetable> get(Caller caller, long id);

    Flux<? extends Timetable> getByHospital(Caller caller, Hospital hospital, Pagination<Instant> pagination);

    Flux<? extends Timetable> getByDoctor(Caller caller, Account doctor, Pagination<Instant> pagination);

    Flux<? extends Timetable> getByRoom(Caller caller, Room room, Pagination<Instant> pagination);

    Mono<? extends Timetable> update(
            Caller caller,
            Timetable target,
            Hospital hospital,
            Account doctor,
            Instant from,
            Instant to,
            Room room);

    Mono<Void> deleteByHospital(Caller caller, Hospital hospital);

    Mono<Void> deleteByDoctor(Caller caller, Account doctor);

    Mono<Void> delete(Caller caller, Timetable target);
}
