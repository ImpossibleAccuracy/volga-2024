package com.simp.service.shared.service.scheme;

import com.simp.service.shared.server.payload.dto.RoomDto;
import reactor.core.publisher.Mono;

public interface RoomControllerScheme {
    Mono<RoomDto> details(long hospital, long room, String token);

    Mono<RoomDto> detailsByName(long hospital, String roomName, String token);
}
