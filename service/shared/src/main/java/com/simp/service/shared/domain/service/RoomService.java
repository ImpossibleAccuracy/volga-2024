package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<? extends Room> get(Caller caller, Hospital hospital, long id);

    Flux<? extends Room> getHospitalRooms(Caller caller, Hospital hospital);
}
