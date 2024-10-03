package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Doctor;

public record DoctorDto(long id, String name) implements Doctor {
}
