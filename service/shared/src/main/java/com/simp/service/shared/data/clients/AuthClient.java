package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.contants.Services;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.server.payload.request.*;
import com.simp.service.shared.server.payload.response.AuthResponse;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ConditionalOnProperty(Services.AUTH_SERVICE_KEY)
@FeignClient(name = "authClient", url = "${" + Services.AUTH_SERVICE_KEY + "}")
public interface AuthClient {
    @PostMapping(value = "/Accounts/SignUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<AuthResponse> signUp(@RequestBody SignUpRequest request);

    @PostMapping(value = "/Accounts/SignIn", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<AuthResponse> signIn(@RequestBody SignInRequest request);

    @PutMapping(value = "/Accounts/SignOut", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<Void> signOut(@RequestHeader(Services.Auth.AUTH_HEADER) String token);

    @GetMapping(value = "/Accounts/Validate", consumes = MediaType.APPLICATION_JSON_VALUE)
    Maybe<Void> validateToken(@RequestParam("accessToken") String token);

    @PostMapping(value = "/Accounts/Refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request);

    @GetMapping(value = "/Accounts/Me", consumes = MediaType.APPLICATION_JSON_VALUE)
    Maybe<Account> getAccount(@RequestHeader(Services.Auth.AUTH_HEADER) String token);

    @PutMapping(value = "/Accounts/Update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<Account> updateAccount(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @RequestBody AccountUpdateRequest request);

    // ADMIN SECTION

    @GetMapping(value = "/Accounts/Update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Flowable<Account> getAccountList(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @PostMapping(value = "/Accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<Account> createAccount(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @RequestBody AccountCreateRequest request);

    @PutMapping(value = "/Accounts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Single<Account> updateAccount(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @PathVariable("id") Long id,
            @RequestBody AccountUpdateFullRequest request);

    @DeleteMapping(value = "/Accounts/{id}")
    Single<Void> deleteAccount(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
