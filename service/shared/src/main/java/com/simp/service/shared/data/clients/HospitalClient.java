package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.contants.Services;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.server.payload.hospital.HospitalCreateUpdateRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import feign.Headers;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Hospital.Name)
@Headers({"Accept: application/json"})
public interface HospitalClient {

    @PostMapping(ApiScheme.HospitalsService.Hospitals)
    Mono<HospitalDto> create(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestBody HospitalCreateUpdateRequest request);

    @GetMapping(ApiScheme.HospitalsService.Hospitals)
    Flux<HospitalDto> getList(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @GetMapping(ApiScheme.HospitalsService.HospitalDetails)
    Mono<HospitalDto> get(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);

    @GetMapping(ApiScheme.HospitalsService.HospitalRooms)
    Mono<HospitalDto> rooms(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id,
            @PathVariable("rom") Long room);

    @PutMapping(ApiScheme.HospitalsService.HospitalDetails)
    Mono<HospitalDto> update(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id,
            @RequestBody HospitalCreateUpdateRequest request);

    @DeleteMapping(ApiScheme.HospitalsService.HospitalDetails)
    Mono<Void> delete(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
