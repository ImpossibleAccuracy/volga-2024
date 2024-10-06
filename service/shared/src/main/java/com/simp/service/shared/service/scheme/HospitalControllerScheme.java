package com.simp.service.shared.service.scheme;

import com.simp.service.shared.server.payload.dto.HospitalDto;
import reactor.core.publisher.Mono;

public interface HospitalControllerScheme {
    Mono<HospitalDto> details(long id, String token);
}
