package com.simp.service.shared.server.security;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.domain.exception.UnauthorizedException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;
import com.simp.service.shared.domain.model.Caller;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

public final class UserHolder {
    public static Mono<Authorization> getAuthorization() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    try {
                        return ((Authorization) ctx.getAuthentication().getPrincipal());
                    } catch (ClassCastException | NullPointerException e) {
                        return null;
                    }
                });
    }

    public static Mono<Account> requireAccount() {
        return getAuthorization()
                .map(Authorization::account)
                .switchIfEmpty(Mono.error(new UnauthorizedException("No user presented")));
    }

    public static Mono<Authorization> requireAuthorization() {
        return getAuthorization()
                .switchIfEmpty(Mono.error(new UnauthorizedException("No user presented")));
    }

    @Deprecated
    public static Mono<Caller> requireCaller(HttpHeaders headers) {
        return requireCaller(headers.getFirst(AuthConstants.AUTH_HEADER));
    }

    public static Mono<Caller> requireCaller(String token) {
        return requireAuthorization()
                .map(auth -> new Caller(
                        token,
                        auth.account(),
                        auth.roles()
                ));
    }
}
