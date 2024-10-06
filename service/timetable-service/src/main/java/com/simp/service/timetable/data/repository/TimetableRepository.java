package com.simp.service.timetable.data.repository;

import com.simp.service.timetable.data.model.TimetableEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface TimetableRepository extends ReactiveCrudRepository<TimetableEntity, Long> {
    @Query(value = """
            select * from "Timetable"
            where deleted = false and
                hospital_id = :hospital and
                date_from >= :start and
                date_to <= :end
            """)
    Flux<TimetableEntity> findByHospitalAndDateRange(long hospital, Instant start, Instant end);

    @Query(value = """
            select * from "Timetable"
            where deleted = false and
                doctor_id = :doctor and
                date_from >= :start and
                date_to <= :end
            """)
    Flux<TimetableEntity> findByDoctorAndDateRange(long doctor, Instant start, Instant end);

    @Query(value = """
            select * from "Timetable"
            where deleted = false and
                room_id = :room and
                date_from >= :start and
                date_to <= :end
            """)
    Flux<TimetableEntity> findByRoomAndDateRange(long room, Instant start, Instant end);

    @Query("""
            update "Timetable"
            set deleted = true
            where hospital_id = :id
            """)
    Mono<Void> deleteSoftByHospital(Long id);

    @Query("""
            update "Timetable"
            set deleted = true
            where doctor_id = :id
            """)
    Mono<Void> deleteSoftByDoctor(Long id);

    @Query("""
            update "Timetable"
            set deleted = true
            where id = :id
            """)
    Mono<Void> deleteSoft(Long id);

    Mono<TimetableEntity> findByIdAndDeletedFalse(long id);
}
