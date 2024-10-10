package com.simp.service.hospital.server.controller;

import com.simp.service.hospital.domain.service.LocalHospitalService;
import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.server.payload.hospital.HospitalCreateUpdateRequest;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.scheme.HospitalControllerScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HospitalController implements HospitalControllerScheme {
    private final LocalHospitalService hospitalService;

    @PostMapping(ApiScheme.HospitalsService.Hospitals)
    public Mono<HospitalDto> newHospital(@RequestHeader HttpHeaders headers,
                                         @RequestBody @Valid HospitalCreateUpdateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> hospitalService.create(
                        caller,
                        request.name(),
                        request.address(),
                        request.contactPhone(),
                        request.rooms()
                ))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.HospitalsService.Hospitals)
    public Flux<HospitalDto> getAll(@RequestHeader HttpHeaders headers,
                                    @Valid PaginationRequest pagination) {
        return UserHolder
                .requireCaller(headers)
                .flatMapMany(caller -> hospitalService.getList(caller, Mappers.fromRequest(pagination)))
                .map(Mappers::toDto);
    }

    @Override
    @GetMapping(ApiScheme.HospitalsService.HospitalDetails)
    public Mono<HospitalDto> details(@PathVariable("id") long id,
                                     @RequestHeader(AuthConstants.AUTH_HEADER) String token) {
        return UserHolder
                .requireCaller(token)
                .flatMap(caller -> hospitalService.get(caller, id))
                .map(Mappers::toDto);
    }

    @PutMapping(ApiScheme.HospitalsService.HospitalDetails)
    public Mono<HospitalDto> update(@RequestHeader HttpHeaders headers,
                                    @PathVariable("id") long id,
                                    @RequestBody @Valid HospitalCreateUpdateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> hospitalService.get(caller, id))
                .flatMap(tuple -> hospitalService.update(
                        tuple.getT1(),
                        tuple.getT2(),
                        request.name(),
                        request.address(),
                        request.contactPhone(),
                        request.rooms()
                ))
                .map(Mappers::toDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.HospitalsService.HospitalDetails)
    public Mono<Void> delete(@RequestHeader HttpHeaders headers,
                             @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> hospitalService.get(caller, id))
                .flatMap(tuple -> hospitalService.delete(tuple.getT1(), tuple.getT2()));
    }
}
