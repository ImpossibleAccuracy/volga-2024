package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.AccountClient;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnBean(AccountClient.class)
@RequiredArgsConstructor
public class NetworkDoctorService implements DoctorService {
    private final AccountClient accountClient;

    @Override
    public Mono<Doctor> getDoctor(Caller caller, Long id) {
        return accountClient
                .getDoctor(caller.token(), id)
                .map(d -> d); // TODO
    }

    @Override
    public Flux<Doctor> getDoctorList(Caller caller, Pagination pagination) {
        return accountClient
                .getDoctorList(caller.token(), pagination.from(), pagination.count())
                .map(d -> d); // TODO;
    }
}
