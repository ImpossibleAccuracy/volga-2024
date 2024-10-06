package com.simp.service.shared.data.service;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.data.clients.HospitalClient;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.HospitalService;
import com.simp.service.shared.server.payload.hospital.HospitalCreateUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@ConditionalOnExpression("${" + Services.Hospital.Key + ":true}")
@RequiredArgsConstructor
public class NetworkHospitalService implements HospitalService {
    private final HospitalClient hospitalClient;

    @Override
    public Mono<? extends Hospital> create(Caller caller, String name, String address, String contactPhone, List<String> rooms) {
        return hospitalClient
                .create(caller.token(), HospitalCreateUpdateRequest.builder()
                        .name(name)
                        .address(address)
                        .contactPhone(contactPhone)
                        .rooms(rooms)
                        .build());
    }

    @Override
    public Mono<? extends Hospital> get(Caller caller, long id) {
        return hospitalClient.get(caller.token(), id);
    }

    @Override
    public Flux<? extends Hospital> getList(Caller caller, Pagination<Integer> pagination) {
        return hospitalClient.getList(caller.token(), pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Hospital> update(Caller caller, Hospital target, String name, String address, String contactPhone, List<String> rooms) {
        return hospitalClient
                .update(caller.token(), target.id(), HospitalCreateUpdateRequest.builder()
                        .name(name)
                        .address(address)
                        .contactPhone(contactPhone)
                        .rooms(rooms)
                        .build());
    }

    @Override
    public Mono<Void> delete(Caller caller, Hospital hospital) {
        return hospitalClient.delete(caller.token(), hospital.id());
    }
}
