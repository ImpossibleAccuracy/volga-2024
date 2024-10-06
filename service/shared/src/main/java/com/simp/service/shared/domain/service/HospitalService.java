package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import reactor.core.publisher.Mono;

public interface HospitalService {
    Mono<? extends Hospital> get(Caller caller, long id);
}
