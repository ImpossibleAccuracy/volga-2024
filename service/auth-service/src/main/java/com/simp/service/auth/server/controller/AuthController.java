package com.simp.service.auth.server.controller;

import com.simp.service.auth.domain.service.LocalAccountService;
import com.simp.service.auth.domain.service.LocalAuthService;
import com.simp.service.auth.domain.service.TokenService;
import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.domain.exception.InvalidArgumentsException;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.account.request.SignInRequest;
import com.simp.service.shared.server.payload.account.request.SignUpRequest;
import com.simp.service.shared.server.payload.account.response.AuthResponse;
import com.simp.service.shared.server.payload.account.response.TokenValidateResponse;
import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import com.simp.service.shared.server.payload.token.RefreshTokenRequest;
import com.simp.service.shared.server.payload.token.ValidateTokenRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.scheme.AuthControllerScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerScheme {
    private final LocalAuthService authService;
    private final LocalAccountService accountService;
    private final TokenService tokenService;

    @PostMapping(ApiScheme.AccountService.Auth.SignUp)
    public Mono<AuthResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        return authService
                .signUp(request.lastName(), request.firstName(), request.username(), request.password())
                .map(a -> new AuthResponse(
                        tokenService.generateToken(a),
                        Mappers.toDto(a)
                ));
    }

    @PostMapping(ApiScheme.AccountService.Auth.SignIn)
    public Mono<AuthResponse> signIn(@RequestBody @Valid SignInRequest request) {
        return authService
                .signIn(request.username(), request.password())
                .map(a -> new AuthResponse(
                        tokenService.generateToken(a),
                        Mappers.toDto(a)
                ));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(ApiScheme.AccountService.Auth.SignOut)
    public Mono<Void> signOut(@RequestHeader HttpHeaders headers) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(authService::signOut);
    }

    @GetMapping(ApiScheme.AccountService.Auth.Validate)
    public Mono<TokenValidateResponse> validate(@Valid ValidateTokenRequest request) {
        return tokenService
                .validateToken(request.accessToken())
                .map(TokenValidateResponse::new);
    }

    @PostMapping(ApiScheme.AccountService.Auth.Refresh)
    public Mono<AuthResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        return tokenService.getTokenData(request.token())
                .flatMap((data) -> {
                    if (data == null) {
                        return Mono.error(new InvalidArgumentsException("Token invalid"));
                    }

                    return Mono.just(data);
                })
                .flatMap(accountService::getAccountUnsecured)
                .map(account -> {
                    var token = tokenService.generateToken(account);

                    return new AuthResponse(
                            token,
                            Mappers.toDto(account)
                    );
                });
    }

    @Override
    @PreAuthorize("hasRole('" + AuthConstants.SERVICE_ROLE + "')")
    @PostMapping(ApiScheme.AccountService.Auth.Full)
    public Mono<AuthorizationDto> getAuthData(@RequestHeader(AuthConstants.AUTH_HEADER) String token) {
        return authService
                .authUser(token)
                .map(a -> new AuthorizationDto(Mappers.toDto(a.account()), a.roles()));
    }
}
