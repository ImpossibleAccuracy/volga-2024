package com.simp.service.hospital.data.repository;

import com.simp.service.hospital.data.model.RoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<RoomEntity, Long> {
    Flux<RoomEntity> findByHospital(long hospital);

    Flux<RoomEntity> findByNameInAndHospital(Collection<String> names, long hospital);
}
