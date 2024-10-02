package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface DoctorService {
    Maybe<Doctor> getDoctor(Caller caller, Long id);

    Flowable<Doctor> getDoctorList(Caller caller, Pagination pagination);
}
