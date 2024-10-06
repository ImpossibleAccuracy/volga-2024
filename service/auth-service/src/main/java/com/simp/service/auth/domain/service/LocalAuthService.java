package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import reactor.core.publisher.Mono;

public interface LocalAuthService extends AuthService {
    Mono<? extends Account> signUp(String lastName,
                                   String firstName,
                                   String username,
                                   String password);

    Mono<? extends Account> signIn(String username, String password);

    Mono<Void> signOut(Caller account);
}
