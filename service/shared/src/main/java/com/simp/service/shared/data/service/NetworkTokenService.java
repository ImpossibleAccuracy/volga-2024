package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AccountClient;
import com.simp.service.shared.domain.service.TokenService;
import com.simp.service.shared.server.payload.account.response.TokenValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnBean(AccountClient.class)
@RequiredArgsConstructor
public class NetworkTokenService implements TokenService {
    private final AccountClient accountClient;

    @Override
    public Mono<Boolean> validateToken(String token) {
        return accountClient
                .validateToken(token)
                .map(TokenValidateResponse::success);
    }
}
