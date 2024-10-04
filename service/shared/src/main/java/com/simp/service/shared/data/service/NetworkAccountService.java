package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AccountClient;
import com.simp.service.shared.data.contants.Services;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.AccountService;
import com.simp.service.shared.server.payload.account.request.AccountCreateRequest;
import com.simp.service.shared.server.payload.account.request.AccountUpdateFullRequest;
import com.simp.service.shared.server.payload.account.request.AccountUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkAccountService implements AccountService {
    private final AccountClient accountClient;

    @Override
    public Mono<? extends Account> newAccount(Caller caller, String firstName, String lastName, String username, String password, List<String> roles) {
        AccountCreateRequest request = new AccountCreateRequest(lastName, firstName, username, password, roles);

        return accountClient.createAccount(caller.token(), request);
    }

    @Override
    public Mono<? extends Account> getAccount(Caller caller, Long id) {
        return accountClient.getAccount(caller.token());
    }

    @Override
    public Flux<? extends Account> getAccountList(Caller caller, Pagination pagination) {
        return accountClient.getAccountList(caller.token(), pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Account> updateAccount(Caller caller, String lastName, String firstName, String password) {
        AccountUpdateRequest request = new AccountUpdateRequest(lastName, firstName, password);

        return accountClient.updateAccount(caller.token(), request);
    }

    @Override
    public Mono<? extends Account> updateAccount(Caller caller, Account target, String username, String lastName, String firstName, String password, List<String> roles) {
        AccountUpdateFullRequest request = new AccountUpdateFullRequest(lastName, firstName, username, password, roles);

        return accountClient.updateAccount(caller.token(), target.id(), request);
    }

    @Override
    public Mono<Void> deleteAccount(Caller caller, Account target) {
        return accountClient.deleteAccount(caller.token(), target.id());
    }
}
