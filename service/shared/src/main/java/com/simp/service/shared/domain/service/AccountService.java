package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<? extends Account> get(Caller caller, long id);
}
