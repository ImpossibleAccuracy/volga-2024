package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.DoctorControllerScheme;
import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DoctorClient implements DoctorControllerScheme {
    private final DoctorClientFeign feign;

    @Override
    public Mono<AccountDto> details(long id, String token) {
        return feign.details(id, token);
    }

    @ReactiveFeignClient(name = Services.Account.Name, qualifier = "DoctorClient")
    @Headers({"Accept: application/json"})
    public interface DoctorClientFeign {
        @GetMapping(ApiScheme.AccountService.Doctors.DoctorDetails)
        Mono<AccountDto> details(@PathVariable("id") long id,
                                 @RequestHeader(AuthConstants.AUTH_HEADER) String token);
    }
}
