package com.simp.service.shared.service.scheme;

import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import reactor.core.publisher.Mono;

public interface AuthControllerScheme {
    Mono<AuthorizationDto> getAuthData(String token);
}
