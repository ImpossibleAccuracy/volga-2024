package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Room;

public record RoomDto(long id, String name, long hospital) implements Room {
}
