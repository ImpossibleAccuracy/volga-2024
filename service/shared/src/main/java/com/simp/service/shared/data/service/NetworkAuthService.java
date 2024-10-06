package com.simp.service.shared.data.service;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.data.clients.AuthClient;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.payload.account.request.SignInRequest;
import com.simp.service.shared.server.payload.account.request.SignUpRequest;
import com.simp.service.shared.server.payload.account.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkAuthService implements AuthService {
    private final AuthClient authClient;

    @Override
    public Mono<? extends Account> signUp(String lastName, String firstName, String username, String password) {
        SignUpRequest request = new SignUpRequest(lastName, firstName, username, password);

        return authClient
                .signUp(request)
                .map(AuthResponse::account);
    }

    @Override
    public Mono<? extends Account> signIn(String username, String password) {
        SignInRequest request = new SignInRequest(username, password);

        return authClient
                .signIn(request)
                .map(AuthResponse::account);
    }

    @Override
    public Mono<Void> signOut(Caller account) {
        return authClient.signOut(account.token());
    }

    @Override
    public Mono<? extends Authorization> authUser(String token) {
        return authClient.getAccountAuthorization(token);
    }
}
