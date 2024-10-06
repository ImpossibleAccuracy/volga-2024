package com.simp.service.hospital.domain.service;

import com.simp.service.hospital.data.model.HospitalEntity;
import com.simp.service.hospital.data.repository.HospitalRepository;
import com.simp.service.shared.domain.exception.ResourceNotFoundException;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final RoomServiceImpl roomService;

    @Override
    public Mono<? extends Hospital> create(Caller caller,
                                           String name,
                                           String address,
                                           String contactPhone,
                                           List<String> rooms) {
        // TODO: check

        return Mono.just(HospitalEntity.builder()
                        .name(name)
                        .address(address)
                        .contactPhone(contactPhone)
                        .build())
                .flatMap(hospitalRepository::save)
                .doOnSuccess(hospital -> roomService
                        .createHospitalRooms(caller, hospital, rooms)
                        .subscribe());
    }

    @Override
    public Mono<? extends Hospital> get(Caller caller, long id) {
        // TODO: check

        return hospitalRepository
                .findByIdAndDeletedFalse(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Hospital not found")));
    }

    @Override
    public Flux<? extends Hospital> getList(Caller caller, Pagination<Integer> pagination) {
        // TODO: check

        return hospitalRepository.findAllPaginated(pagination.from(), pagination.count());
    }

    @Override
    public Mono<? extends Hospital> update(Caller caller,
                                           Hospital target,
                                           String name,
                                           String address,
                                           String contactPhone,
                                           List<String> rooms) {
        // TODO: check

        var targetEntity = target instanceof HospitalEntity ? Mono.just((HospitalEntity) target) : hospitalRepository.findById(target.id());

        return targetEntity
                .map(s -> HospitalEntity.builder()
                        .id(s.id())
                        .createdAt(s.createdAt())
                        .creator(s.creator())
                        .name(name)
                        .address(address)
                        .contactPhone(contactPhone)
                        // TODO: add rooms
                        .build())
                .flatMap(hospitalRepository::save)
                .doOnSuccess(hospital -> roomService
                        .createHospitalRooms(caller, hospital, rooms)
                        .subscribe());
    }

    @Override
    public Mono<Void> delete(Caller caller, Hospital hospital) {
        // TODO: check

        return hospitalRepository.deleteSoft(hospital.id());
    }
}
