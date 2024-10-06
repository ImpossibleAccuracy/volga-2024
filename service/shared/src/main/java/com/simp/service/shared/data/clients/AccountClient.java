package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.contants.Services;
import com.simp.service.shared.server.payload.account.request.AccountCreateRequest;
import com.simp.service.shared.server.payload.account.request.AccountUpdateFullRequest;
import com.simp.service.shared.server.payload.account.request.AccountUpdateRequest;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.scheme.ApiScheme;
import feign.Headers;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name, qualifier = "AccountClient")
@Headers({"Accept: application/json"})
public interface AccountClient {
    @GetMapping(ApiScheme.AccountService.Account.Me)
    Mono<AccountDto> getAccount(@RequestHeader(AuthConstants.AUTH_HEADER) String token);

    @PutMapping(ApiScheme.AccountService.Account.Update)
    Mono<AccountDto> updateAccount(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestBody AccountUpdateRequest request);

    // ADMIN SECTION

    @GetMapping(ApiScheme.AccountService.Account.Accounts)
    Flux<AccountDto> getAccountList(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @PostMapping(ApiScheme.AccountService.Account.Accounts)
    Mono<AccountDto> createAccount(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @RequestBody AccountCreateRequest request);

    @PutMapping(ApiScheme.AccountService.Account.AccountDetails)
    Mono<AccountDto> updateAccount(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id,
            @RequestBody AccountUpdateFullRequest request);

    @DeleteMapping(ApiScheme.AccountService.Account.AccountDetails)
    Mono<Void> deleteAccount(
            @RequestHeader(AuthConstants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
