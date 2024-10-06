package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import com.simp.service.shared.service.scheme.RoomControllerScheme;
import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
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

    @ReactiveFeignClient(name = Services.Hospital.Name, qualifier = "RoomClient")
    @Headers({"Accept: application/json"})
    public interface RoomClientFeign {
        @GetMapping(ApiScheme.HospitalsService.HospitalRoomDetails)
        Mono<RoomDto> details(@PathVariable("id") long hospital,
                              @PathVariable("room") long room,
                              @RequestHeader(AuthConstants.AUTH_HEADER) String token);

        @GetMapping(ApiScheme.HospitalsService.HospitalRoomNameDetails)
        Mono<RoomDto> detailsByName(@PathVariable("id") long hospital,
                                    @PathVariable("room") String roomName,
                                    @RequestHeader(AuthConstants.AUTH_HEADER) String token);
    }
}
