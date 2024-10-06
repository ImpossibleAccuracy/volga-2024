package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import reactor.core.publisher.Flux;

public interface LocalDoctorService extends DoctorService {
    Flux<? extends Account> getList(Caller caller, String nameFilter, Pagination<Integer> pagination);
}
