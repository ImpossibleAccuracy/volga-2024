package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import reactor.core.publisher.Mono;

public interface TokenService {
    Mono<Long> getTokenData(String token);

    String generateToken(Account account);

    Mono<Boolean> validateToken(String token);
}
