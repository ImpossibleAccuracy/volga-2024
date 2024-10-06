package com.simp.service.shared.data.service;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import com.simp.service.shared.domain.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Hospital.Key + ":true}")
@RequiredArgsConstructor
public class NetworkRoomService implements RoomService {
    @Override
    public Mono<? extends Room> get(Caller caller, Hospital hospital, long id) {
        return null;
    }

    @Override
    public Mono<? extends Room> getByName(Caller caller, Hospital hospital, String name) {
        return null;
    }

    @Override
    public Flux<? extends Room> getHospitalRooms(Caller caller, Hospital hospital) {
        return null;
    }
}
