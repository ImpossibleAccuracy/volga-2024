package com.simp.service.shared.server.payload.hospital;

import lombok.Builder;

import java.util.List;

@Builder
public record HospitalCreateUpdateRequest(String name, String address, String contactPhone, List<String> rooms) {
}
