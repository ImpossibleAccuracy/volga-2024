package com.simp.service.auth.server.controller;

import com.simp.service.auth.domain.service.AccountServiceImpl;
import com.simp.service.auth.domain.service.ExtendedTokenService;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.domain.service.TokenService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.account.request.SignInRequest;
import com.simp.service.shared.server.payload.account.request.SignUpRequest;
import com.simp.service.shared.server.payload.account.response.AuthResponse;
import com.simp.service.shared.server.payload.account.response.TokenValidateResponse;
import com.simp.service.shared.server.payload.token.RefreshTokenRequest;
import com.simp.service.shared.server.payload.token.ValidateTokenRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AccountServiceImpl accountService;
    private final TokenService tokenService;
    private final ExtendedTokenService extendedTokenService;

    @PostMapping(ApiScheme.AccountService.Auth.SignUp)
    public Mono<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return authService
                .signUp(request.lastName(), request.firstName(), request.username(), request.password())
                .map(a -> new AuthResponse(
                        extendedTokenService.generateToken(a),
                        Mappers.toDto(a)
                ));
    }

    @PostMapping(ApiScheme.AccountService.Auth.SignIn)
    public Mono<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return authService
                .signIn(request.username(), request.password())
                .map(a -> new AuthResponse(
                        extendedTokenService.generateToken(a),
                        Mappers.toDto(a)
                ));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(ApiScheme.AccountService.Auth.SignOut)
    public Mono<Void> signOut(@RequestHeader HttpHeaders headers) {
        return authService.signOut(UserHolder.requireCaller(headers));
    }

    @GetMapping(ApiScheme.AccountService.Auth.Validate)
    public Mono<TokenValidateResponse> validate(ValidateTokenRequest request) {
        return tokenService
                .validateToken(request.accessToken())
                .map(TokenValidateResponse::new);
    }

    @PostMapping(ApiScheme.AccountService.Auth.Refresh)
    public Mono<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        var accountId = extendedTokenService.getTokenUser(request.token());

        return accountService.getAccountUnsecured(accountId)
                .map(account -> {
                    var token = extendedTokenService.generateToken(account);

                    return new AuthResponse(
                            token,
                            Mappers.toDto(account)
                    );
                });
    }
}
