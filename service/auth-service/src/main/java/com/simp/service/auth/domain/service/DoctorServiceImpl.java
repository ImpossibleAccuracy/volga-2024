package com.simp.service.auth.domain.service;

import com.simp.service.auth.data.repository.AccountRepository;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.security.Roles;
import com.simp.service.shared.domain.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final AccountRepository accountRepository;

    @Override
    public Mono<? extends Account> get(Caller caller, Long id) {
        return accountRepository
                .findByIdAndRole(id, Roles.DOCTOR.dbName())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Doctor not found")));
    }

    @Override
    public Flux<? extends Account> getList(Caller caller, String nameFilter, Pagination<Integer> pagination) {
        if (nameFilter == null || nameFilter.isBlank()) {
            return accountRepository.findAllByNameLikeAndRoleExistsPaginated(
                    Roles.DOCTOR.dbName(),
                    pagination.from(),
                    pagination.count());
        } else {
            return accountRepository.findAllByNameLikeAndRoleExistsPaginated(
                    "%" + nameFilter + "%",
                    Roles.DOCTOR.dbName(),
                    pagination.from(),
                    pagination.count());
        }
    }
}
