package com.simp.service.history.data.repository;

import com.simp.service.history.data.model.HistoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface HistoryRepository extends ReactiveCrudRepository<HistoryEntity, Long> {
    Flux<HistoryEntity> findByPatient(long patient);
}