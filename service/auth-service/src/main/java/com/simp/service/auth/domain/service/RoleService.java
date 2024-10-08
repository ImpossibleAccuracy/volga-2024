package com.simp.service.auth.domain.service;

import com.simp.service.auth.data.model.RoleEntity;
import com.simp.service.shared.domain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleService {
    void verifyRoles(List<String> roles);

    Mono<Void> setAccountRoles(Account account, List<String> roles);

    Flux<RoleEntity> getByAccount(Account account);
}
