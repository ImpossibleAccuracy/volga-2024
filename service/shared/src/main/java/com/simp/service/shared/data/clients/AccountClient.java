package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.clients.feign.AccountClientFeign;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.service.scheme.AccountControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountClient implements AccountControllerScheme {
    private final AccountClientFeign feign;

    @Override
    public Mono<AccountDto> get(long id, String token) {
        return feign.get(token, id);
    }
}
