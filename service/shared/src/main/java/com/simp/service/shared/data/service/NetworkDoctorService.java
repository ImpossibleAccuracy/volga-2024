package com.simp.service.shared.data.service;

import com.simp.service.shared.data.clients.DoctorsClient;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.service.DoctorService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(DoctorsClient.class)
@RequiredArgsConstructor
public class NetworkDoctorService implements DoctorService {
    private final DoctorsClient doctorsClient;

    @Override
    public Maybe<Doctor> getDoctor(Caller caller, Long id) {
        return doctorsClient.getDoctor(caller.token(), id);
    }

    @Override
    public Flowable<Doctor> getDoctorList(Caller caller, Pagination pagination) {
        return doctorsClient.getDoctorList(caller.token(), pagination.from(), pagination.count());
    }
}
