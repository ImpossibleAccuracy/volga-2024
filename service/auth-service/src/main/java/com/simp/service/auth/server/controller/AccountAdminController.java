package com.simp.service.auth.server.controller;

import com.simp.service.auth.domain.service.LocalAccountService;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.account.request.AccountCreateRequest;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AccountAdminController {
    private final LocalAccountService accountService;

    // TODO: admin
    @PostMapping(ApiScheme.AccountService.Account.Accounts)
    public Mono<AccountDto> create(@RequestHeader HttpHeaders headers,
                                   @RequestBody AccountCreateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> accountService
                        .create(
                                caller,
                                request.firstName(),
                                request.lastName(),
                                request.username(),
                                request.password(),
                                request.roles()))
                .map(Mappers::toDto);
    }

    // TODO: admin
    @GetMapping(ApiScheme.AccountService.Account.Accounts)
    public Flux<AccountDto> getAll(@RequestHeader HttpHeaders headers,
                                   PaginationRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMapMany(caller -> accountService
                        .getList(caller, Mappers.fromRequest(request)))
                .map(Mappers::toDto);
    }

    // TODO: admin
    @PutMapping(ApiScheme.AccountService.Account.AccountDetails)
    public Mono<AccountDto> updateAccount(@PathVariable("id") long id,
                                          @RequestHeader HttpHeaders headers,
                                          @RequestBody AccountCreateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> accountService
                        .get(caller, id)
                        .flatMap(target -> accountService.update(
                                caller,
                                target,
                                request.firstName(),
                                request.lastName(),
                                request.username(),
                                request.password(),
                                request.roles())
                        ))
                .map(Mappers::toDto);
    }

    // TODO: admin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.AccountService.Account.AccountDetails)
    public Mono<Void> deleteAccount(@PathVariable("id") long id,
                                    @RequestHeader HttpHeaders headers) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> accountService
                        .get(caller, id)
                        .flatMap(target -> accountService.delete(caller, target)));
    }
}
