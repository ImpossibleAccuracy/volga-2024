package com.simp.service.shared.data.service;

import com.simp.service.shared.domain.model.Authorization;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.AuthControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkAuthService implements AuthService {
    private final AuthControllerScheme authClient;

    @Override
    public Mono<? extends Authorization> authUser(String token) {
        return authClient.getAuthData(token);
    }
}
