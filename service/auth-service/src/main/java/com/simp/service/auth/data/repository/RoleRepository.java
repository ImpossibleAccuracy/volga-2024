package com.simp.service.auth.data.repository;

import com.simp.service.auth.data.model.RoleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {
    @Query(value = """
            select r.* from "Role" r
            inner join "Role_Account" ra on ra.role_id = r.id
            where ra.account_id = :id
            """)
    Flux<RoleEntity> findByAccountId(Long id);

    Flux<RoleEntity> findByTitleInIgnoreCase(Collection<String> titles);
}
