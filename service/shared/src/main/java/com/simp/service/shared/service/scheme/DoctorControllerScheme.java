package com.simp.service.shared.service.scheme;

import com.simp.service.shared.server.payload.dto.AccountDto;
import reactor.core.publisher.Mono;

public interface DoctorControllerScheme {
    Mono<AccountDto> details(long id, String token);
}
