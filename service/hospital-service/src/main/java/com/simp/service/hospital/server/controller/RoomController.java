package com.simp.service.hospital.server.controller;

import com.simp.service.hospital.domain.service.LocalHospitalService;
import com.simp.service.hospital.domain.service.LocalRoomService;
import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.shared.service.scheme.RoomControllerScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RoomController implements RoomControllerScheme {
    private final LocalRoomService roomService;
    private final LocalHospitalService hospitalService;

    @GetMapping(ApiScheme.HospitalsService.HospitalRooms)
    public Flux<RoomDto> getAllRooms(@RequestHeader HttpHeaders headers,
                                     @PathVariable("id") long id,
                                     @Valid PaginationRequest pagination) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> hospitalService.get(caller, id))
                .flatMapMany(tuple -> roomService.getHospitalRooms(tuple.getT1(), tuple.getT2()))
                .map(Mappers::toDto);
    }

    @Override
    @PreAuthorize("hasRole('" + AuthConstants.SERVICE_ROLE + "')")
    @GetMapping(ApiScheme.HospitalsService.HospitalRoomDetails)
    public Mono<RoomDto> details(@PathVariable("id") long hospital,
                                 @PathVariable("room") long room,
                                 @RequestHeader(AuthConstants.AUTH_HEADER) String token) {
        return UserHolder
                .requireCaller(token)
                .zipWhen(caller -> hospitalService.get(caller, hospital))
                .flatMap(tuple -> roomService.get(tuple.getT1(), tuple.getT2(), room))
                .map(Mappers::toDto);

    }

    @Override
    @PreAuthorize("hasRole('" + AuthConstants.SERVICE_ROLE + "')")
    @GetMapping(ApiScheme.HospitalsService.HospitalRoomNameDetails)
    public Mono<RoomDto> detailsByName(@PathVariable("id") long hospital,
                                       @PathVariable("room") String name,
                                       @RequestHeader(AuthConstants.AUTH_HEADER) String token) {
        return UserHolder
                .requireCaller(token)
                .zipWhen(caller -> hospitalService.get(caller, hospital))
                .flatMap(tuple -> roomService.getByName(tuple.getT1(), tuple.getT2(), name))
                .map(Mappers::toDto);
    }
}
