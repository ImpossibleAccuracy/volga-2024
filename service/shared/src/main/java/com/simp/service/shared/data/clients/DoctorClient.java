package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.contants.Services;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.scheme.ApiScheme;
import feign.Headers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name, qualifier = "DoctorClient")
@Headers({"Accept: application/json"})
public interface DoctorClient {
    @GetMapping(ApiScheme.AccountService.Doctors.Doctors)
    Flux<AccountDto> getDoctorList(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestParam("nameFilter") String nameFilter,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @GetMapping(ApiScheme.AccountService.Doctors.DoctorDetails)
    Mono<AccountDto> getDoctor(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
