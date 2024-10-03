package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Account> signUp(String lastName,
                         String firstName,
                         String username,
                         String password);

    Mono<Account> signIn(String username, String password);

    Mono<Void> signOut(Caller account);
}
