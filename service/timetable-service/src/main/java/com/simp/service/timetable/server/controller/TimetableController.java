package com.simp.service.timetable.server.controller;

import com.simp.service.shared.domain.service.DoctorService;
import com.simp.service.shared.domain.service.HospitalService;
import com.simp.service.shared.domain.service.RoomService;
import com.simp.service.shared.domain.service.TimetableService;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.TimetableDto;
import com.simp.service.shared.server.payload.shared.DatePaginationRequest;
import com.simp.service.shared.server.payload.timetable.TimetableCreateUpdateRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TimetableController {
    private final TimetableService timetableService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;
    private final RoomService roomService;

    @PostMapping(ApiScheme.TimetableService.Timetable)
    public Mono<TimetableDto> create(@RequestHeader HttpHeaders headers,
                                     @RequestBody TimetableCreateUpdateRequest request) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> {
                    var hospitalMono = hospitalService.get(caller, request.hospitalId());
                    var roomMono = hospitalMono.flatMap(hospital -> roomService.getByName(caller, hospital, request.room()));
                    var doctorMono = doctorService.get(caller, request.doctorId());

                    return Mono.zip(Mono.just(caller), hospitalMono, doctorMono, roomMono);
                })
                .flatMap(tuple -> timetableService.create(
                        tuple.getT1(),
                        tuple.getT2(),
                        tuple.getT3(),
                        request.from(),
                        request.to(),
                        tuple.getT4()
                ))
                .map(Mappers::toDto);
    }

    @DeleteMapping(ApiScheme.TimetableService.TimetableDoctor)
    public Flux<TimetableDto> getByDoctor(@RequestHeader HttpHeaders headers,
                                          @PathVariable("id") long id,
                                          DatePaginationRequest request) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller ->
                        doctorService.get(caller, id)
                )
                .flatMapMany(tuple -> timetableService.getByDoctor(
                        tuple.getT1(),
                        tuple.getT2(),
                        Mappers.fromRequest(request)))
                .map(Mappers::toDto);
    }

    @DeleteMapping(ApiScheme.TimetableService.TimetableHospital)
    public Flux<TimetableDto> getByHospital(@RequestHeader HttpHeaders headers,
                                            @PathVariable("id") long id,
                                            DatePaginationRequest request) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller ->
                        hospitalService.get(caller, id)
                )
                .flatMapMany(tuple -> timetableService.getByHospital(
                        tuple.getT1(),
                        tuple.getT2(),
                        Mappers.fromRequest(request)))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.TimetableService.TimetableHospitalRoom)
    public Flux<TimetableDto> getByRoom(@RequestHeader HttpHeaders headers,
                                        @PathVariable("id") long hospitalId,
                                        @PathVariable("room") long roomId,
                                        DatePaginationRequest request) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> hospitalService.get(caller, hospitalId))
                .flatMap(tuple -> Mono.zip(
                        Mono.just(tuple.getT1()),
                        roomService.get(tuple.getT1(), tuple.getT2(), roomId)))
                .flatMapMany(tuple -> timetableService.getByRoom(
                        tuple.getT1(),
                        tuple.getT2(),
                        Mappers.fromRequest(request)))
                .map(Mappers::toDto);
    }

    @PutMapping(ApiScheme.TimetableService.TimetableDetails)
    public Mono<TimetableDto> update(@RequestHeader HttpHeaders headers,
                                     @RequestBody TimetableCreateUpdateRequest request,
                                     @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .flatMap(caller -> {
                    var targetMono = timetableService.get(caller, id);

                    var hospitalMono = hospitalService.get(caller, request.hospitalId());
                    var roomMono = hospitalMono.flatMap(hospital -> roomService.getByName(caller, hospital, request.room()));
                    var doctorMono = doctorService.get(caller, request.doctorId());

                    return Mono.zip(Mono.just(caller), targetMono, hospitalMono, doctorMono, roomMono);
                })
                .flatMap(tuple -> timetableService.update(
                        tuple.getT1(),
                        tuple.getT2(),
                        tuple.getT3(),
                        tuple.getT4(),
                        request.from(),
                        request.to(),
                        tuple.getT5()
                ))
                .map(Mappers::toDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.TimetableService.TimetableDetails)
    public Mono<Void> delete(@RequestHeader HttpHeaders headers,
                             @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> timetableService.get(caller, id))
                .flatMap(tuple -> timetableService.delete(tuple.getT1(), tuple.getT2()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.TimetableService.TimetableDoctor)
    public Mono<Void> deleteByDoctor(@RequestHeader HttpHeaders headers,
                                     @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller ->
                        doctorService.get(caller, id)
                )
                .flatMap(tuple -> timetableService.deleteByDoctor(tuple.getT1(), tuple.getT2()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.TimetableService.TimetableHospital)
    public Mono<Void> deleteByHospital(@RequestHeader HttpHeaders headers,
                                       @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller ->
                        hospitalService.get(caller, id)
                )
                .flatMap(tuple -> timetableService.deleteByHospital(tuple.getT1(), tuple.getT2()));
    }
}
