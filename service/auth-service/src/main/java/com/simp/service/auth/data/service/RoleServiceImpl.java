package com.simp.service.auth.data.service;

import com.simp.service.auth.data.model.RoleEntity;
import com.simp.service.auth.data.model.ref.RoleAccountRef;
import com.simp.service.auth.data.repository.RoleAccountRepository;
import com.simp.service.auth.data.repository.RoleRepository;
import com.simp.service.auth.domain.service.RoleService;
import com.simp.service.shared.data.model.BaseEntity;
import com.simp.service.shared.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleAccountRepository roleAccountRepository;

    @Override
    public Mono<Void> setAccountRoles(Account account, List<String> roles) {
        return roleRepository
                .findByAccountId(account.id())
                .collectList()
                .flatMapMany(savedRoles -> {
                    var newRolesTitles = roles.stream()
                            .filter(r -> savedRoles
                                    .stream()
                                    .noneMatch(r2 -> r.equalsIgnoreCase(r2.title())))
                            .toList();

                    var newRoles = roleRepository.findByTitleInIgnoreCase(newRolesTitles)
                            .map(role -> RoleAccountRef
                                    .builder()
                                    .role(role.id())
                                    .account(account.id())
                                    .build())
                            .collectList();

                    var deletedRoles = savedRoles
                            .stream()
                            .filter(role -> roles
                                    .stream()
                                    .noneMatch(t -> t.equalsIgnoreCase(role.title())))
                            .map(BaseEntity::id)
                            .toList();

                    return Mono.zip(newRoles, Mono.just(deletedRoles));
                })
                .flatMap(tuple -> {
                    var createMono = tuple.getT1().isEmpty() ? null : roleAccountRepository
                            .saveAll(tuple.getT1())
                            .collectList();

                    var deleteMono = tuple.getT2().isEmpty() ? null : roleAccountRepository
                            .deleteByAccountAndRoleIn(account.id(), tuple.getT2());

                    if (createMono != null && deleteMono != null) return Mono.zip(createMono, deleteMono);
                    else if (createMono != null) return createMono;
                    else if (deleteMono != null) return deleteMono;

                    return Mono.just(false);
                })
                .then();
    }

    @Override
    public Flux<RoleEntity> getByAccount(Account account) {
        return roleRepository.findByAccountId(account.id());
    }
}
