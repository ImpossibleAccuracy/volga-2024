package com.simp.service.shared.data.service;

import com.simp.service.shared.domain.model.Caller;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Room;
import com.simp.service.shared.domain.service.RoomService;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.RoomControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnExpression("${" + Services.Hospital.Key + ":true}")
@RequiredArgsConstructor
public class NetworkRoomService implements RoomService {
    private final RoomControllerScheme roomClient;

    @Override
    public Mono<? extends Room> get(Caller caller, Hospital hospital, long id) {
        return roomClient.details(hospital.id(), id, caller.token());
    }

    @Override
    public Mono<? extends Room> getByName(Caller caller, Hospital hospital, String name) {
        return roomClient.detailsByName(hospital.id(), name, caller.token());
    }
}
