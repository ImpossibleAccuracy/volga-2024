package com.simp.service.shared.data.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.service.AccountService;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.AccountControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Account.Key + ":true}")
@RequiredArgsConstructor
public class NetworkAccountService implements AccountService {
    private final AccountControllerScheme client;

    @Override
    public Mono<? extends Account> get(Caller caller, long id) {
        return client.get(id, caller.token());
    }
}
