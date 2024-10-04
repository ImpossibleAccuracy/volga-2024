package com.simp.service.hospital.data.repository;

import com.simp.service.hospital.data.model.HospitalEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HospitalRepository extends ReactiveCrudRepository<HospitalEntity, Long> {
    Mono<HospitalEntity> findByIdAndDeletedFalse(long id);

    @Query(value = """
            select * from "Hospital"
            where deleted = false
            limit :limit offset :offset
            """)
    Flux<HospitalEntity> findAllPaginated(int offset, int limit);

    @Query("""
            update "Hospital"
            set deleted = true
            where id = :id
            """)
    Mono<Void> deleteSoft(Long id);
}
