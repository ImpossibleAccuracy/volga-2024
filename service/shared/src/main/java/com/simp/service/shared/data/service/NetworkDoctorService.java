package com.simp.service.shared.data.service;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.data.clients.DoctorClient;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkDoctorService implements DoctorService {
    private final DoctorClient doctorClient;

    @Override
    public Mono<? extends Account> get(Caller caller, Long id) {
        return doctorClient.getDoctor(caller.token(), id);
    }

    @Override
    public Flux<? extends Account> getList(Caller caller, String nameFilter, Pagination<Integer> pagination) {
        return doctorClient.getDoctorList(caller.token(), nameFilter, pagination.from(), pagination.count());
    }
}
