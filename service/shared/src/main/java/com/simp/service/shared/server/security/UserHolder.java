package com.simp.service.shared.server.security;

import com.simp.service.shared.data.contants.AuthContants;
import com.simp.service.shared.domain.exception.UnauthorizedException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserHolder {
    public static Account getCurrentAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Account requireAccount() {
        Account account = getCurrentAccount();

        if (account == null) {
            throw new UnauthorizedException("No user presented");
        }

        return account;
    }

    public static Caller requireCaller(HttpHeaders headers) {
        Account account = getCurrentAccount();

        if (account == null) {
            throw new UnauthorizedException("No user presented");
        }

        return new Caller(
                headers.getFirst(AuthContants.AUTH_HEADER),
                account
        );
    }
}
