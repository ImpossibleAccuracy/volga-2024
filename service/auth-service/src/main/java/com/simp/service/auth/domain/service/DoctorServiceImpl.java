package com.simp.service.auth.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    @Override
    public Mono<Doctor> getDoctor(Caller caller, Long id) {
        return null;
    }

    @Override
    public Flux<Doctor> getDoctorList(Caller caller, Pagination pagination) {
        return null;
    }
}
