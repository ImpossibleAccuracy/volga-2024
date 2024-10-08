package com.simp.service.auth.data.repository;

import com.simp.service.auth.data.model.RevokedTokenEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TokenRepository extends ReactiveCrudRepository<RevokedTokenEntity, Long> {
    Mono<Boolean> existsByToken(String token);
}
