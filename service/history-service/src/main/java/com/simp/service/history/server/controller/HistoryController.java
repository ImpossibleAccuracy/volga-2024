package com.simp.service.history.server.controller;

import com.simp.service.history.domain.service.LocalHistoryService;
import com.simp.service.shared.domain.service.AccountService;
import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.shared.domain.service.HospitalService;
import com.simp.service.shared.domain.service.RoomService;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.HistoryDto;
import com.simp.service.shared.server.payload.history.HistoryCreateUpdateRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HistoryController {
    private final LocalHistoryService historyService;
    private final AccountService accountService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final RoomService roomService;

    @PostMapping(ApiScheme.HistoryService.History)
    public Mono<HistoryDto> create(@RequestHeader HttpHeaders headers,
                                   @RequestBody @Valid HistoryCreateUpdateRequest request) {
        return UserHolder.requireCaller(headers)
                .flatMap(caller -> {
                    var patient = accountService.get(caller, request.patientId());
                    var doctor = doctorService.get(caller, request.doctorId());
                    var hospital = hospitalService.get(caller, request.hospitalId());
                    var room = hospital.flatMap(h -> roomService.getByName(caller, h, request.room()));

                    return Mono.zip(
                            Mono.just(caller),
                            patient,
                            doctor,
                            room
                    );
                })
                .flatMap(tuple -> historyService.create(
                        tuple.getT1(),
                        request.date(),
                        tuple.getT2(),
                        tuple.getT3(),
                        tuple.getT4(),
                        request.data()))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.HistoryService.HistoryDetails)
    public Mono<HistoryDto> get(@RequestHeader HttpHeaders headers,
                                @PathVariable("id") long id) {
        return UserHolder.requireCaller(headers)
                .flatMap(caller -> historyService.get(caller, id))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.HistoryService.HistoryAccount)
    public Flux<HistoryDto> list(@RequestHeader HttpHeaders headers,
                                 @PathVariable("id") long id) {
        return UserHolder.requireCaller(headers)
                .flatMap(caller -> Mono.zip(
                        Mono.just(caller),
                        accountService.get(caller, id)
                ))
                .flatMapMany(tuple -> historyService.getByAccount(tuple.getT1(), tuple.getT2()))
                .map(Mappers::toDto);
    }

    @PostMapping(ApiScheme.HistoryService.HistoryDetails)
    public Mono<HistoryDto> create(@RequestHeader HttpHeaders headers,
                                   @PathVariable("id") long id,
                                   @RequestBody @Valid HistoryCreateUpdateRequest request) {
        return UserHolder.requireCaller(headers)
                .flatMap(caller -> {
                    var target = historyService.get(caller, id);

                    var patient = accountService.get(caller, request.patientId());
                    var doctor = doctorService.get(caller, request.doctorId());
                    var hospital = hospitalService.get(caller, request.hospitalId());
                    var room = hospital.flatMap(h -> roomService.getByName(caller, h, request.room()));

                    return Mono.zip(
                            Mono.just(caller),
                            target,
                            patient,
                            doctor,
                            room
                    );
                })
                .flatMap(tuple -> historyService.update(
                        tuple.getT1(),
                        tuple.getT2(),
                        request.date(),
                        tuple.getT3(),
                        tuple.getT4(),
                        tuple.getT5(),
                        request.data()))
                .map(Mappers::toDto);
    }
}
