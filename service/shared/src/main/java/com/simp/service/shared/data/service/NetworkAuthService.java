package com.simp.service.shared.data.service;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.data.clients.AuthClient;
import com.simp.service.shared.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(Services.AUTH)
@RequiredArgsConstructor
public class NetworkAuthService implements AuthService {
    private final AuthClient authClient;

    @Override
    @NotNull
    public String getGreeting(@NotNull String name) {
        return authClient.greetings(name).greeting();
    }
}
