package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Timetable;
import reactor.core.publisher.Mono;

public interface TimetableService {
    Mono<? extends Timetable> get(Caller caller, long id);
}
