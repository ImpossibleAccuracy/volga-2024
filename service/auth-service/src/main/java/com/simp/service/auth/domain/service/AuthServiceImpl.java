package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public Maybe<Account> signUp(String lastName, String firstName, String username, String password) {
        return null;
    }

    @Override
    public Maybe<Account> signIn(String username, String password) {
        return null;
    }

    @Override
    public Single<Void> signOut(Caller account) {
        return null;
    }
}
