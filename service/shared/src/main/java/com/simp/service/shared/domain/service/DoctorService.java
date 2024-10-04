package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoctorService {
    Mono<? extends Account> getDoctor(Caller caller, Long id);

    Flux<? extends Account> getDoctorList(Caller caller, String nameFilter, Pagination pagination);
}
