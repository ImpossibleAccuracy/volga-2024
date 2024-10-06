package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.contants.Services;
import com.simp.service.shared.server.payload.account.request.SignInRequest;
import com.simp.service.shared.server.payload.account.request.SignUpRequest;
import com.simp.service.shared.server.payload.account.response.AuthResponse;
import com.simp.service.shared.server.payload.account.response.TokenValidateResponse;
import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import com.simp.service.shared.server.payload.token.RefreshTokenRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import feign.Headers;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name, qualifier = "AuthClient")
@Headers({"Accept: application/json"})
public interface AuthClient {
    @PostMapping(ApiScheme.AccountService.Auth.SignUp)
    Mono<AuthResponse> signUp(@RequestBody SignUpRequest request);

    @PostMapping(ApiScheme.AccountService.Auth.SignIn)
    Mono<AuthResponse> signIn(@RequestBody SignInRequest request);

    @PutMapping(ApiScheme.AccountService.Auth.SignOut)
    Mono<Void> signOut(@RequestHeader(AuthConstants.AUTH_HEADER) String token);

    @GetMapping(ApiScheme.AccountService.Auth.Validate)
    Mono<TokenValidateResponse> validateToken(@RequestParam("accessToken") String token);

    @PostMapping(ApiScheme.AccountService.Auth.Refresh)
    Mono<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request);

    // TODO: need to additionally secure endpoint
    @PostMapping(ApiScheme.AccountService.Auth.Full)
    Mono<AuthorizationDto> getAccountAuthorization(@RequestHeader(AuthConstants.AUTH_HEADER) String token);
}
