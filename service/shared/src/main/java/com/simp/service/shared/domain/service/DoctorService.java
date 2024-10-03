package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoctorService {
    Mono<Doctor> getDoctor(Caller caller, Long id);

    Flux<Doctor> getDoctorList(Caller caller, Pagination pagination);
}
