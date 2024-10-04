package com.simp.service.auth.data.repository;

import com.simp.service.auth.data.model.AccountEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
    @Query(value = """
            select a.* from "Account" a
            inner join "Role_Account" ra on ra.account_id = a.id
            inner join "Role" r on r.id = ra.role_id
            where a.id = :id
                and a.deleted = false
                and r.title = :role
            """)
    Mono<AccountEntity> findByIdAndRole(Long id, String role);

    @Query(value = """
            select * from "Account"
            where deleted = false
            limit :limit offset :offset
            """)
    Flux<AccountEntity> findAllPaginated(int offset, int limit);

    @Query(value = """
            select a.* from "Account" a
            inner join "Role_Account" ra on ra.account_id = a.id
            inner join "Role" r on r.id = ra.role_id
            where a.deleted = false
                and r.title = :role
            limit :limit offset :offset
            """)
    Flux<AccountEntity> findAllByNameLikeAndRoleExistsPaginated(String role, int offset, int limit);

    @Query(value = """
            select a.* from "Account" a
            inner join "Role_Account" ra on ra.account_id = a.id
            inner join "Role" r on r.id = ra.role_id
            where CONCAT_WS(' ', a.first_name, a.last_name) like :name
                and a.deleted = false
                and r.title = :role
            limit :limit offset :offset
            """)
    Flux<AccountEntity> findAllByNameLikeAndRoleExistsPaginated(String name, String role, int offset, int limit);

    Mono<AccountEntity> findByUsernameIgnoreCaseAndDeletedFalse(String username);

    Mono<Boolean> existsByUsernameAndDeletedFalse(String username);

    Mono<AccountEntity> findByIdAndDeletedFalse(Long id);

    @Query("""
            update "Account"
            set deleted = true
            where id = :id
            """)
    Mono<Void> deleteSoft(Long id);
}