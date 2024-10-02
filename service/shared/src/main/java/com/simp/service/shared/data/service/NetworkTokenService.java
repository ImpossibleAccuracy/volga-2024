package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AuthClient;
import com.simp.service.shared.domain.service.TokenService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(AuthClient.class)
@RequiredArgsConstructor
public class NetworkTokenService implements TokenService {
    private final AuthClient authClient;

    @Override
    public Maybe<Void> validateToken(String token) {
        return authClient.validateToken(token);
    }
}
