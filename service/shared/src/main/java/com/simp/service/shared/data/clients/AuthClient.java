package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.AuthControllerScheme;
import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClient implements AuthControllerScheme {
    private final AuthClientFeign feign;

    @Override
    public Mono<AuthorizationDto> getAuthData(String token) {
        return feign.getAuthData(token);
    }

    @ReactiveFeignClient(name = Services.Account.Name, qualifier = "AuthClient")
    @Headers({"Accept: application/json"})
    public interface AuthClientFeign {
        // TODO: need to additionally secure endpoint
        @PostMapping(ApiScheme.AccountService.Auth.Full)
        Mono<AuthorizationDto> getAuthData(@RequestHeader(AuthConstants.AUTH_HEADER) String token);
    }
}
