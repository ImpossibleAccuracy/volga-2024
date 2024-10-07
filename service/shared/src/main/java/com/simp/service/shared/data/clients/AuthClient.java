package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.clients.feign.AuthClientFeign;
import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import com.simp.service.shared.service.scheme.AuthControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClient implements AuthControllerScheme {
    private final AuthClientFeign feign;

    @Override
    public Mono<AuthorizationDto> getAuthData(String token) {
        return feign.getAuthData(token);
    }
}
