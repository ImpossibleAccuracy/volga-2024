package com.simp.service.shared.server.mapper;

import com.simp.service.shared.domain.model.*;
import com.simp.service.shared.server.payload.dto.*;
import com.simp.service.shared.server.payload.shared.DatePaginationRequest;
import com.simp.service.shared.server.payload.shared.PaginationRequest;

import java.time.Instant;

public final class Mappers {
    public static AccountDto toDto(Account item) {
        if (item instanceof AccountDto) return (AccountDto) item;

        return new AccountDto(item.id(), item.firstName(), item.lastName(), item.username());
    }

    public static HospitalDto toDto(Hospital item) {
        if (item instanceof HospitalDto) return (HospitalDto) item;

        return new HospitalDto(item.id(), item.name(), item.address(), item.contactPhone());
    }

    public static RoomDto toDto(Room item) {
        if (item instanceof RoomDto) return (RoomDto) item;

        return new RoomDto(item.id(), item.name(), item.hospital());
    }

    public static TimetableDto toDto(Timetable item) {
        if (item instanceof TimetableDto) return (TimetableDto) item;

        return new TimetableDto(item.id(), item.hospital(), item.doctor(), item.from(), item.to(), item.room());
    }

    public static HistoryDto toDto(History item) {
        if (item instanceof HistoryDto) return (HistoryDto) item;

        return new HistoryDto(item.id(), item.date(), item.patient(), item.doctor(), item.room(), item.data());
    }

    public static AppointmentDto toDto(Appointment item) {
        if (item instanceof AppointmentDto) return (AppointmentDto) item;

        return new AppointmentDto(item.id(), item.creator(), item.timetable(), item.time());
    }

    public static Pagination<Integer> fromRequest(PaginationRequest request) {
        return new Pagination<>(request.from, request.count);
    }

    public static Pagination<Instant> fromRequest(DatePaginationRequest request) {
        return new Pagination<>(request.from, request.to);
    }
}
