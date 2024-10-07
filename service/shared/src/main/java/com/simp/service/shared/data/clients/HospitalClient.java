package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.clients.feign.HospitalClientFeign;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.service.scheme.HospitalControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HospitalClient implements HospitalControllerScheme {
    private final HospitalClientFeign feign;

    @Override
    public Mono<HospitalDto> details(long id, String token) {
        return feign.details(id, token);
    }
}
