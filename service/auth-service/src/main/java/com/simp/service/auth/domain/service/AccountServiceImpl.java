package com.simp.service.auth.domain.service;

import com.simp.service.auth.data.model.AccountEntity;
import com.simp.service.auth.data.repository.AccountRepository;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<? extends Account> newAccount(Caller caller, String firstName, String lastName, String username, String password, List<String> roles) {
        // TODO: check admin

        AccountEntity account = AccountEntity.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                // TODO: add roles
                .build();

        return accountRepository.save(account);
    }

    public Mono<? extends Account> getAccountUnsecured(Long id) {
        return accountRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Account not found")));
    }

    @Override
    public Mono<? extends Account> getAccount(Caller caller, Long id) {
        // TODO: check access
        return getAccountUnsecured(id);
    }

    @Override
    public Flux<? extends Account> getAccountList(Caller caller, Pagination pagination) {
        return accountRepository.findAllPaginated(pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Account> updateAccount(Caller caller, String lastName, String firstName, String password) {
        AccountEntity update = AccountEntity.builder()
                .id(caller.account().id())
                .username(caller.account().username())
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                .build();

        return accountRepository.save(update);
    }

    @Override
    public Mono<? extends Account> updateAccount(Caller caller,
                                                 Account target,
                                                 String username,
                                                 String lastName,
                                                 String firstName,
                                                 String password,
                                                 List<String> roles) {
        // TODO: check access

        AccountEntity update = AccountEntity.builder()
                .id(target.id())
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                // TODO: add roles
                .build();

        return accountRepository.save(update);
    }

    @Override
    public Mono<Void> deleteAccount(Caller caller, Account target) {
        // TODO: check access

        return accountRepository.deleteAccountSoft(target.id());
    }
}
