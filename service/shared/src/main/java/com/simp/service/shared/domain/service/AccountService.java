package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface AccountService {
    Single<Account> newAccount(
            Caller caller,
            String lastName,
            String firstName,
            String username,
            String password,
            List<String> roles);

    Maybe<Account> getAccount(Caller caller, Long id);

    Flowable<Account> getAccountList(Caller caller, Pagination pagination);

    Single<Account> updateAccount(
            Caller caller,
            String lastName,
            String firstName,
            String password);

    Single<Account> updateAccount(
            Caller caller,
            Account target,
            String username,
            String lastName,
            String firstName,
            String password,
            List<String> roles);

    Single<Void> deleteAccount(Caller caller, Account target);
}
