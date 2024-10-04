package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AccountClient;
import com.simp.service.shared.data.contants.Services;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkDoctorService implements DoctorService {
    private final AccountClient accountClient;

    @Override
    public Mono<? extends Account> getDoctor(Caller caller, Long id) {
        return accountClient.getDoctor(caller.token(), id);
    }

    @Override
    public Flux<? extends Account> getDoctorList(Caller caller, String nameFilter, Pagination pagination) {
        return accountClient.getDoctorList(caller.token(), nameFilter, pagination.from(), pagination.count());
    }
}
