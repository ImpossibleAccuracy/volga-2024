package com.simp.service.hospital.server.controller;

import com.simp.service.shared.domain.service.HospitalService;
import com.simp.service.shared.domain.service.RoomService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final HospitalService hospitalService;

    @GetMapping(ApiScheme.HospitalsService.HospitalRooms)
    public Flux<RoomDto> getAllRooms(@RequestHeader HttpHeaders headers,
                                     @PathVariable("id") long id,
                                     PaginationRequest pagination) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> hospitalService.getHospital(caller, id))
                .flatMapMany(tuple -> roomService.getHospitalRooms(tuple.getT1(), tuple.getT2()))
                .map(Mappers::toDto);
    }
}
