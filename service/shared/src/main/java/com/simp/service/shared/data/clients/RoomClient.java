package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.clients.feign.RoomClientFeign;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.service.scheme.RoomControllerScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoomClient implements RoomControllerScheme {
    private final RoomClientFeign feign;

    @Override
    public Mono<RoomDto> details(long hospital, long room, String token) {
        return feign.details(hospital, room, token);
    }

    @Override
    public Mono<RoomDto> detailsByName(long hospital, String roomName, String token) {
        return feign.detailsByName(hospital, roomName, token);
    }
}
