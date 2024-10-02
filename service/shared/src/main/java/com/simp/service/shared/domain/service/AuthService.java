package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface AuthService {
    Maybe<Account> signUp(String lastName,
                          String firstName,
                          String username,
                          String password);

    Maybe<Account> signIn(String username, String password);

    Single<Void> signOut(Caller account);
}
