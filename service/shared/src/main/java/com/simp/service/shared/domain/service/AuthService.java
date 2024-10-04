package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;
import com.simp.service.shared.domain.model.Caller;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<? extends Account> signUp(String lastName,
                                   String firstName,
                                   String username,
                                   String password);

    Mono<? extends Account> signIn(String username, String password);

    Mono<Void> signOut(Caller account);

    Mono<? extends Authorization> authUser(String token);
}
