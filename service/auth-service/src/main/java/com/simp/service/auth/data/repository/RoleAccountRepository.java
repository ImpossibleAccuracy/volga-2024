package com.simp.service.auth.data.repository;

import com.simp.service.auth.data.model.ref.RoleAccountRef;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Repository
public interface RoleAccountRepository extends ReactiveCrudRepository<RoleAccountRef, Long> {
    Mono<Void> deleteByAccountAndRoleIn(long account, Collection<Long> roles);
}
