package com.simp.service.shared.data.clients.feign;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.AuthorizationDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name, qualifier = "AuthClient", configuration = FeignClient.class)
@Headers({"Accept: application/json"})
public interface AuthClientFeign {
    @PostMapping(ApiScheme.AccountService.Auth.Full)
    Mono<AuthorizationDto> getAuthData(@RequestHeader(AuthConstants.AUTH_HEADER) String token);
}
