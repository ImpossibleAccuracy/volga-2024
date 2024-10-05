package com.simp.service.auth.server.controller;

import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.doctor.DoctorsFilterRequest;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DoctorsController {
    private final DoctorService doctorService;

    @GetMapping(ApiScheme.AccountService.Doctors.Doctors)
    public Flux<AccountDto> getAll(@RequestHeader HttpHeaders headers,
                                   DoctorsFilterRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMapMany(caller -> doctorService
                        .getList(caller, request.nameFilter, Mappers.fromRequest(request)))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.AccountService.Doctors.DoctorDetails)
    public Mono<AccountDto> details(@PathVariable("id") long id,
                                    @RequestHeader HttpHeaders headers,
                                    DoctorsFilterRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> doctorService
                        .get(caller, id))
                .map(Mappers::toDto);
    }
}
