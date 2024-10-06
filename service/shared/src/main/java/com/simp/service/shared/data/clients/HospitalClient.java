package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.HospitalControllerScheme;
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
public class HospitalClient implements HospitalControllerScheme {
    private final HospitalClientFeign feign;

    @Override
    public Mono<HospitalDto> details(long id, String token) {
        return feign.details(id, token);
    }

    @ReactiveFeignClient(name = Services.Hospital.Name, qualifier = "HospitalClient")
    @Headers({"Accept: application/json"})
    public interface HospitalClientFeign {
        @GetMapping(ApiScheme.HospitalsService.HospitalDetails)
        Mono<HospitalDto> details(@PathVariable("id") long id, @RequestHeader(AuthConstants.AUTH_HEADER) String token);
    }
}
