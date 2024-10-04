package com.simp.service.shared.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import reactor.core.publisher.Flux;

public interface RoomService {
    Flux<? extends Room> getHospitalRooms(Caller caller, Hospital hospital);
}
