package com.simp.service.auth.server.controller;

import com.simp.service.shared.domain.service.AccountService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.account.request.AccountUpdateRequest;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(ApiScheme.AccountService.Account.Me)
    public Mono<AccountDto> getMe() {
        return UserHolder
                .requireAccount()
                .map(Mappers::toDto);
    }

    @PutMapping(ApiScheme.AccountService.Account.Update)
    public Mono<AccountDto> updateMe(@RequestHeader HttpHeaders headers,
                                     @RequestBody AccountUpdateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> accountService
                        .updateAccount(caller, request.firstName(), request.lastName(), request.password()))
                .map(Mappers::toDto);
    }
}
