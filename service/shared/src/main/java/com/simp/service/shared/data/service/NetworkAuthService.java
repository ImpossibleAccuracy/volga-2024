package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AuthClient;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.payload.request.SignInRequest;
import com.simp.service.shared.server.payload.request.SignUpRequest;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConditionalOnBean(AuthClient.class)
@RequiredArgsConstructor
public class NetworkAuthService implements AuthService {
    private final AuthClient authClient;

    @Override
    public Maybe<Account> signUp(String lastName, String firstName, String username, String password) {
        SignUpRequest request = new SignUpRequest(lastName, firstName, username, password);

        return authClient
                .signUp(request)
                .mapOptional((authResponse -> Optional.ofNullable(authResponse.account())));
    }

    @Override
    public Maybe<Account> signIn(String username, String password) {
        SignInRequest request = new SignInRequest(username, password);

        return authClient
                .signIn(request)
                .mapOptional((authResponse -> Optional.ofNullable(authResponse.account())));
    }

    @Override
    public Single<Void> signOut(Caller account) {
        return authClient.signOut(account.token());
    }
}
