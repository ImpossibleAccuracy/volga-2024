package com.simp.service.timetable.server.controller;

import com.simp.service.shared.server.mapper.Mappers;
import com.simp.service.shared.server.payload.dto.AppointmentDto;
import com.simp.service.shared.server.payload.dto.DateRangeDto;
import com.simp.service.shared.server.payload.timetable.CreateAppointmentsRequest;
import com.simp.service.shared.server.security.UserHolder;
import com.simp.service.shared.service.ApiScheme;
import com.simp.service.timetable.domain.service.LocalAppointmentService;
import com.simp.service.timetable.domain.service.LocalTimetableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final LocalTimetableService timetableService;
    private final LocalAppointmentService appointmentService;

    @PostMapping(ApiScheme.TimetableService.TimetableAppointments)
    public Mono<AppointmentDto> create(@RequestHeader HttpHeaders headers,
                                       @RequestBody @Valid CreateAppointmentsRequest request,
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(ApiScheme.TimetableService.Appointment)
    public Mono<Void> delete(@RequestHeader HttpHeaders headers,
                             @PathVariable("id") long id) {
        return UserHolder
                .requireCaller(headers)
                .zipWhen(caller -> appointmentService.get(caller, id))
                .flatMap(tuple -> appointmentService.delete(tuple.getT1(), tuple.getT2()));
    }
}
