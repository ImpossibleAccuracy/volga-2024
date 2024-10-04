package com.simp.service.auth.domain.service;

import com.simp.service.auth.data.model.AccountEntity;
import com.simp.service.auth.data.model.RoleEntity;
import com.simp.service.auth.data.repository.AccountRepository;
import com.simp.service.auth.data.repository.RoleRepository;
import com.simp.service.auth.domain.model.AuthorizationImpl;
import com.simp.service.shared.domain.exception.InvalidArgumentsException;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final TokenServiceImpl tokenService; // TODO
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<? extends Account> signUp(String lastName, String firstName, String username, String password) {
        return Mono.just(username)
                .flatMap(accountRepository::existsByUsernameAndDeletedFalse)
                .handle((exists, sync) -> {
                    if (exists) {
                        sync.error(new InvalidArgumentsException("Username already taken"));
                    } else {
                        sync.next(false);
                    }
                })
                .map((a) -> AccountEntity.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .firstName(firstName)
                        .lastName(lastName)
                        .build())
                .flatMap(accountRepository::save);
    }

    @Override
    public Mono<? extends Account> signIn(String username, String password) {
        return Mono.just(username)
                .flatMap(accountRepository::findByUsernameIgnoreCaseAndDeletedFalse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Invalid credentials")))
                .handle((account, sync) -> {
                    if (account == null || !passwordEncoder.matches(password, account.password())) {
                        sync.error(new ResourceNotFoundException("Invalid credentials"));
                    } else {
                        sync.next(account);
                    }
                });
    }

    @Override
    public Mono<Void> signOut(Caller account) {
        // TODO

        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public Mono<? extends Authorization> authUser(String token) {
        return tokenService
                .getTokenData(token)
                .flatMap(accountRepository::findByIdAndDeletedFalse)
                .zipWhen(account -> roleRepository.findByAccountId(account.id()).collectList())
                .map(tuple -> {
                    var roles = tuple.getT2()
                            .stream()
                            .map(RoleEntity::title)
                            .toList();

                    return new AuthorizationImpl(tuple.getT1(), roles);
                });
    }
}
