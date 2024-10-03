package com.simp.service.shared.domain.service;

import reactor.core.publisher.Mono;

public interface TokenService {
    Mono<Boolean> validateToken(String token);
}
