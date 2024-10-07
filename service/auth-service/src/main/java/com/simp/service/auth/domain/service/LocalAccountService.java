package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LocalAccountService extends AccountService {
    Mono<? extends Account> create(
            Caller caller,
            String firstName,
            String lastName,
            String username,
            String password,
            List<String> roles);

    Mono<? extends Account> getAccountUnsecured(long id);

    Flux<? extends Account> getList(Caller caller, Pagination<Integer> pagination);

    Mono<? extends Account> update(
            Caller caller,
            String lastName,
            String firstName,
            String password);

    Mono<? extends Account> update(
            Caller caller,
            Account target,
            String username,
            String lastName,
            String firstName,
            String password,
            List<String> roles);

    Mono<Void> delete(Caller caller, Account target);
}
