package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.payload.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public Mono<Account> signUp(String lastName, String firstName, String username, String password) {
        return null;
    }

    @Override
    public Mono<Account> signIn(String username, String password) {
        return Mono.just(new AccountDto(2, "FN", "LN", "UN"));
    }

    @Override
    public Mono<Void> signOut(Caller account) {
        return null;
    }
}
