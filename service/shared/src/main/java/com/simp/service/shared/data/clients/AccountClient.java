package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.contants.AuthContants;
import com.simp.service.shared.data.contants.Services;
import com.simp.service.shared.server.payload.account.request.*;
import com.simp.service.shared.server.payload.account.response.AuthResponse;
import com.simp.service.shared.server.payload.account.response.TokenValidateResponse;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.payload.dto.DoctorDto;
import com.simp.service.shared.server.payload.token.RefreshTokenRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import feign.Headers;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Account.Name)
@Headers({"Accept: application/json"})
public interface AccountClient {
    @PostMapping(ApiScheme.AccountService.Auth.SignUp)
    Mono<AuthResponse> signUp(@RequestBody SignUpRequest request);

    @PostMapping(ApiScheme.AccountService.Auth.SignIn)
    Mono<AuthResponse> signIn(@RequestBody SignInRequest request);

    @PutMapping(ApiScheme.AccountService.Auth.SignOut)
    Mono<Void> signOut(@RequestHeader(AuthContants.AUTH_HEADER) String token);

    @GetMapping(ApiScheme.AccountService.Auth.Validate)
    Mono<TokenValidateResponse> validateToken(@RequestParam("accessToken") String token);

    @PostMapping(ApiScheme.AccountService.Auth.Refresh)
    Mono<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request);

    @GetMapping(ApiScheme.AccountService.Account.Me)
    Mono<AccountDto> getAccount(@RequestHeader(AuthContants.AUTH_HEADER) String token);

    @PutMapping(ApiScheme.AccountService.Account.Update)
    Mono<AccountDto> updateAccount(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @RequestBody AccountUpdateRequest request);

    // ADMIN SECTION

    @GetMapping(ApiScheme.AccountService.Account.Accounts)
    Flux<AccountDto> getAccountList(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @PostMapping(ApiScheme.AccountService.Account.Accounts)
    Mono<AccountDto> createAccount(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @RequestBody AccountCreateRequest request);

    @PutMapping(ApiScheme.AccountService.Account.AccountDetails)
    Mono<AccountDto> updateAccount(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @PathVariable("id") Long id,
            @RequestBody AccountUpdateFullRequest request);

    @DeleteMapping(ApiScheme.AccountService.Account.AccountDetails)
    Mono<Void> deleteAccount(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);

    // DOCTORS

    @GetMapping(ApiScheme.AccountService.Doctors.Doctors)
    Flux<DoctorDto> getDoctorList(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @GetMapping(ApiScheme.AccountService.Doctors.DoctorDetails)
    Mono<DoctorDto> getDoctor(
            @RequestHeader(AuthContants.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
