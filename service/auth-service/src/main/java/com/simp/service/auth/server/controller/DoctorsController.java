package com.simp.service.auth.server.controller;

import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.doctor.DoctorsFilterRequest;
import com.simp.service.shared.server.payload.dto.DoctorDto;
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
    public Flux<DoctorDto> getAll(@RequestHeader HttpHeaders headers,
                                  DoctorsFilterRequest request) {
        var caller = UserHolder.requireCaller(headers);

        return doctorService
                .getDoctorList(caller, Mappers.fromRequest(request))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.AccountService.Doctors.DoctorDetails)
    public Mono<DoctorDto> details(@PathVariable("id") long id,
                                   @RequestHeader HttpHeaders headers,
                                   DoctorsFilterRequest request) {
        var caller = UserHolder.requireCaller(headers);

        return doctorService
                .getDoctor(caller, id)
                .map(Mappers::toDto);
    }
}
