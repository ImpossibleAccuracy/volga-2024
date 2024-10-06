package com.simp.service.hospital.data.repository;

import com.simp.service.hospital.data.model.RoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<RoomEntity, Long> {
    Flux<RoomEntity> findByHospital(long hospital);

    Mono<RoomEntity> findByIdAndHospital(long id, long hospital);

    Mono<RoomEntity> findByNameAndHospital(String name, long hospital);
}
