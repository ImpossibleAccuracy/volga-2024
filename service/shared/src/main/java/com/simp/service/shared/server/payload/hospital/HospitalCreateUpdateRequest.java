package com.simp.service.shared.server.payload.hospital;

import java.util.List;

public record HospitalCreateUpdateRequest(String name, String address, String contactPhone, List<String> rooms) {
}
