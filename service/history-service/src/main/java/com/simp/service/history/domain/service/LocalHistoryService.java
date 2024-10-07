package com.simp.service.history.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.History;
import com.simp.service.shared.domain.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface LocalHistoryService {
    Mono<? extends History> create(Caller caller, Instant date, Account patient, Account doctor, Room room, String data);

    Mono<? extends History> get(Caller caller, long id);

    Flux<? extends History> getByAccount(Caller caller, Account account);

    Mono<? extends History> update(Caller caller, History target, Instant date, Account patient, Account doctor, Room room, String data);
}
