package com.simp.service.shared.server.mapper.dto;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Hospital;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.domain.model.Room;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.payload.dto.HospitalDto;
import com.simp.service.shared.server.payload.dto.RoomDto;
import com.simp.service.shared.server.payload.shared.PaginationRequest;

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

    public static Pagination fromRequest(PaginationRequest request) {
        return new Pagination(request.getFrom(), request.getCount());
    }
}
