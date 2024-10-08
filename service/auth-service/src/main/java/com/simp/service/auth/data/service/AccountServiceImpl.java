package com.simp.service.auth.data.service;

import com.simp.service.auth.data.model.AccountEntity;
import com.simp.service.auth.data.repository.AccountRepository;
import com.simp.service.auth.domain.service.LocalAccountService;
import com.simp.service.auth.domain.service.RoleService;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements LocalAccountService {
    private final RoleService roleService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<? extends Account> create(Caller caller, String firstName, String lastName, String username, String password, List<String> roles) {
        // TODO: check admin

        AccountEntity account = AccountEntity.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                // TODO: add roles
                .build();

        return accountRepository
                .save(account)
                .doOnSuccess(a -> roleService.setAccountRoles(a, roles).subscribe());
    }

    @Override
    public Mono<? extends Account> getAccountUnsecured(long id) {
        return accountRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Account not found")));
    }

    @Override
    public Mono<? extends Account> get(Caller caller, long id) {
        // TODO: check access
        return getAccountUnsecured(id);
    }

    @Override
    public Flux<? extends Account> getList(Caller caller, Pagination<Integer> pagination) {
        return accountRepository.findAllPaginated(pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Account> update(Caller caller, String lastName, String firstName, String password) {
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
    public Mono<? extends Account> update(Caller caller,
                                          Account target,
                                          String username,
                                          String lastName,
                                          String firstName,
                                          String password,
                                          List<String> roles) {
        // TODO: check access

        var entity = (AccountEntity) target;

        AccountEntity update = entity.toBuilder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(passwordEncoder.encode(password))
                .build();

        return accountRepository
                .save(update)
                .doOnSuccess(a -> roleService.setAccountRoles(a, roles).subscribe());
    }

    @Override
    public Mono<Void> delete(Caller caller, Account target) {
        // TODO: check access

        return accountRepository.deleteSoft(target.id());
    }
}
