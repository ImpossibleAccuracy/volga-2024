package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final ExtendedTokenService extendedTokenService;

    @Override
    public Mono<Boolean> validateToken(String token) {
        return Mono.fromCallable(
                () -> extendedTokenService.getTokenUser(token) != null);
    }
}
