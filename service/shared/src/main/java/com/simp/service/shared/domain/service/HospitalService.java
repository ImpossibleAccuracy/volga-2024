package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HospitalService {
    Mono<? extends Hospital> create(
            Caller caller,
            String name,
            String address,
            String contactPhone,
            List<String> rooms);

    Mono<? extends Hospital> get(Caller caller, long id);

    Flux<? extends Hospital> getList(Caller caller, Pagination<Integer> pagination);

    Mono<? extends Hospital> update(
            Caller caller,
            Hospital target,
            String name,
            String address,
            String contactPhone,
            List<String> rooms);

    Mono<Void> delete(Caller caller, Hospital hospital);
}
