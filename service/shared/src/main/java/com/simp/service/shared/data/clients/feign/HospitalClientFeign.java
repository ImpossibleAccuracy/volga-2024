package com.simp.service.shared.data.clients.feign;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Hospital.Name, qualifier = "HospitalClient", configuration = FeignClient.class)
@Headers({"Accept: application/json"})
public interface HospitalClientFeign {
    @GetMapping(ApiScheme.HospitalsService.HospitalDetails)
    Mono<HospitalDto> details(@PathVariable("id") long id, @RequestHeader(AuthConstants.AUTH_HEADER) String token);
}
