package com.simp.service.shared.data.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.service.HospitalService;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.HospitalControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Hospital.Key + ":true}")
@RequiredArgsConstructor
public class NetworkHospitalService implements HospitalService {
    private final HospitalControllerScheme hospitalClient;

    @Override
    public Mono<? extends Hospital> get(Caller caller, long id) {
        return hospitalClient.details(id, caller.token());
    }
}
