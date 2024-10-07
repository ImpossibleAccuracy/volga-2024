package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.clients.feign.DoctorClientFeign;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.service.scheme.DoctorControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DoctorClient implements DoctorControllerScheme {
    private final DoctorClientFeign feign;

    @Override
    public Mono<AccountDto> details(long id, String token) {
        return feign.details(id, token);
    }
}
