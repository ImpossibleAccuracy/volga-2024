package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Hospital;

public record HospitalDto(long id, String name, String address, String contactPhone) implements Hospital {
}
