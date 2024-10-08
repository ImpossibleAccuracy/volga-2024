package com.simp.service.timetable.data.repository;

import com.simp.service.timetable.data.model.AppointmentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface AppointmentRepository extends ReactiveCrudRepository<AppointmentEntity, Long> {
    Flux<AppointmentEntity> findByTimetableAndDeletedFalse(long timetable);

    Mono<AppointmentEntity> findByIdAndDeletedFalse(long id);

    @Query("""
            update "Appointments"
            set deleted = true
            where id = :id
            """)
    Mono<Void> deleteSoft(Long id);

    Mono<Boolean> existsByTimetableAndDeletedFalse(long timetable);

    Mono<Boolean> existsByTimeBetweenAndTimetableAndDeletedFalse(Instant timeStart, Instant timeEnd, long timetable);
}
