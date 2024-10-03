package com.simp.service.shared.server.mapper.dto;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Doctor;
import com.simp.service.shared.domain.model.Pagination;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import com.simp.service.shared.server.payload.dto.AccountDto;
import com.simp.service.shared.server.payload.dto.DoctorDto;

public final class Mappers {
    public static AccountDto toDto(Account account) {
        if (account instanceof AccountDto) return (AccountDto) account;

        return new AccountDto(account.id(), account.firstName(), account.lastName(), account.username());
    }

    public static DoctorDto toDto(Doctor doctor) {
        if (doctor instanceof DoctorDto) return (DoctorDto) doctor;

        return new DoctorDto(doctor.id(), doctor.name());
    }

    public static Pagination fromRequest(PaginationRequest request) {
        return new Pagination(request.getFrom(), request.getCount());
    }
}
