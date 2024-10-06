package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Authorization;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<? extends Authorization> authUser(String token);
}
