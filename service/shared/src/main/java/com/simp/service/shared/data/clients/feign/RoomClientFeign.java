package com.simp.service.shared.data.clients.feign;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.Services;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = Services.Hospital.Name, qualifier = "RoomClient", configuration = FeignClient.class)
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
