package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AuthClient;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.AccountService;
import com.simp.service.shared.server.payload.request.AccountCreateRequest;
import com.simp.service.shared.server.payload.request.AccountUpdateFullRequest;
import com.simp.service.shared.server.payload.request.AccountUpdateRequest;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnBean(AuthClient.class)
@RequiredArgsConstructor
public class NetworkAccountService implements AccountService {
    private final AuthClient authClient;

    @Override
    public Single<Account> newAccount(Caller caller, String lastName, String firstName, String username, String password, List<String> roles) {
        AccountCreateRequest request = new AccountCreateRequest(lastName, firstName, username, password, roles);

        return authClient.createAccount(caller.token(), request);
    }

    @Override
    public Maybe<Account> getAccount(Caller caller, Long id) {
        return authClient.getAccount(caller.token());
    }

    @Override
    public Flowable<Account> getAccountList(Caller caller, Pagination pagination) {
        return authClient.getAccountList(caller.token(), pagination.from(), pagination.count());
    }

    @Override
    public Single<Account> updateAccount(Caller caller, String lastName, String firstName, String password) {
        AccountUpdateRequest request = new AccountUpdateRequest(lastName, firstName, password);

        return authClient.updateAccount(caller.token(), request);
    }

    @Override
    public Single<Account> updateAccount(Caller caller, Account target, String username, String lastName, String firstName, String password, List<String> roles) {
        AccountUpdateFullRequest request = new AccountUpdateFullRequest(lastName, firstName, username, password, roles);

        return authClient.updateAccount(caller.token(), target.getId(), request);
    }

    @Override
    public Single<Void> deleteAccount(Caller caller, Account target) {
        return authClient.deleteAccount(caller.token(), target.getId());
    }
}
