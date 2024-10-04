package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HospitalService {
    Mono<? extends Hospital> newHospital(
            Caller caller,
            String name,
            String address,
            String contactPhone,
            List<String> rooms);

    Mono<? extends Hospital> getHospital(Caller caller, long id);

    Flux<? extends Hospital> getHospitalList(Caller caller, Pagination pagination);

    Mono<? extends Hospital> updateHospital(
            Caller caller,
            Hospital source,
            String name,
            String address,
            String contactPhone,
            List<String> rooms);

    Mono<Void> deleteHospital(Caller caller, Hospital hospital);
}
