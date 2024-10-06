package com.simp.service.shared.data.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.DoctorControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkDoctorService implements DoctorService {
    private final DoctorControllerScheme doctorClient;

    @Override
    public Mono<? extends Account> get(Caller caller, Long id) {
        return doctorClient.details(id, caller.token());
    }
}
