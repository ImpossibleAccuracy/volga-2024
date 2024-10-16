package com.simp.service.auth.data.service;

import com.simp.service.auth.data.repository.AccountRepository;
import com.simp.service.auth.domain.service.LocalDoctorService;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.security.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements LocalDoctorService {
    private final AccountRepository accountRepository;

    @Override
    public Mono<? extends Account> get(Caller caller, Long id) {
        return accountRepository
                .findByIdAndRole(id, UserRole.DOCTOR.name())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Doctor not found")));
    }

    @Override
    public Flux<? extends Account> getList(Caller caller, String nameFilter, Pagination<Integer> pagination) {
        if (nameFilter == null || nameFilter.isBlank()) {
            return accountRepository.findAllByNameLikeAndRoleExistsPaginated(
                    UserRole.DOCTOR.name(),
                    pagination.from(),
                    pagination.count());
        } else {
            return accountRepository.findAllByNameLikeAndRoleExistsPaginated(
                    "%" + nameFilter + "%",
                    UserRole.DOCTOR.name(),
                    pagination.from(),
                    pagination.count());
        }
    }
}
