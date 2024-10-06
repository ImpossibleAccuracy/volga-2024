package com.simp.service.timetable.server.controller;

import com.simp.service.shared.domain.service.AppointmentService;
import com.simp.service.shared.domain.service.TimetableService;
import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.AppointmentDto;
import com.simp.service.shared.server.payload.dto.DateRangeDto;
import com.simp.service.shared.server.payload.timetable.CreateAppointmentsRequest;
import com.simp.service.shared.server.scheme.ApiScheme;
import com.simp.service.shared.server.security.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final TimetableService timetableService;
    private final AppointmentService appointmentService;

    @PostMapping(ApiScheme.TimetableService.TimetableAppointments)
    public Mono<AppointmentDto> create(@RequestHeader HttpHeaders headers,
                                       @RequestBody CreateAppointmentsRequest request,
                                       @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> timetableService.get(caller, id))
                .flatMap(tuple -> appointmentService.create(tuple.getT1(), tuple.getT2(), request.time()))
                .map(Mappers::toDto);
    }

    @GetMapping(ApiScheme.TimetableService.TimetableAppointments)
    public Flux<DateRangeDto> getAppointments(@RequestHeader HttpHeaders headers,
                                              @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> timetableService.get(caller, id))
                .flatMapMany(tuple -> appointmentService.buildFreeAppointments(tuple.getT1(), tuple.getT2()))
                .map(range -> new DateRangeDto(range.from(), range.to()));
    }

    @DeleteMapping(ApiScheme.TimetableService.Appointment)
    public Mono<Void> create(@RequestHeader HttpHeaders headers,
                             @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> appointmentService.get(caller, id))
                .flatMap(tuple -> appointmentService.delete(tuple.getT1(), tuple.getT2()));
    }
}
