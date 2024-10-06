package com.simp.service.hospital.domain.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import com.simp.service.shared.domain.service.RoomService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LocalRoomService extends RoomService {
    Mono<Void> createHospitalRooms(Caller caller, Hospital hospital, List<String> rooms);

    Flux<? extends Room> getHospitalRooms(Caller caller, Hospital hospital);
}
