package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccountService {
    Mono<Account> newAccount(
            Caller caller,
            String lastName,
            String firstName,
            String username,
            String password,
            List<String> roles);

    Mono<Account> getAccount(Caller caller, Long id);

    Flux<Account> getAccountList(Caller caller, Pagination pagination);

    Mono<Account> updateAccount(
            Caller caller,
            String lastName,
            String firstName,
            String password);

    Mono<Account> updateAccount(
            Caller caller,
            Account target,
            String username,
            String lastName,
            String firstName,
            String password,
            List<String> roles);

    Mono<Void> deleteAccount(Caller caller, Account target);
}
