package com.simp.service.shared.domain.service;

import io.reactivex.rxjava3.core.Maybe;

public interface TokenService {
    Maybe<Void> validateToken(String token);

//    Single<String> generateToken(Account account);
}
