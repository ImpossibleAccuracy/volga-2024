package com.simp.service.shared.data.clients.feign;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import feign.Headers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name, qualifier = "AccountClient")
@Headers({"Accept: application/json"})
public interface AccountClientFeign {
    @GetMapping(ApiScheme.AccountService.Account.AccountDetails)
    Mono<AccountDto> get(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") long id);
}
