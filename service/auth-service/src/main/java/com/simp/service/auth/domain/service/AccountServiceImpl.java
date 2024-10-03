package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Override
    public Mono<Account> newAccount(Caller caller, String lastName, String firstName, String username, String password, List<String> roles) {
        return null;
    }

    public Mono<Account> getAccountUnsecured(Long id) {
        return null;
    }

    @Override
    public Mono<Account> getAccount(Caller caller, Long id) {
        return null;
    }

    @Override
    public Flux<Account> getAccountList(Caller caller, Pagination pagination) {
        return null;
    }

    @Override
    public Mono<Account> updateAccount(Caller caller, String lastName, String firstName, String password) {
        return null;
    }

    @Override
    public Mono<Account> updateAccount(Caller caller, Account target, String username, String lastName, String firstName, String password, List<String> roles) {
        return null;
    }

    @Override
    public Mono<Void> deleteAccount(Caller caller, Account target) {
        return null;
    }
}
