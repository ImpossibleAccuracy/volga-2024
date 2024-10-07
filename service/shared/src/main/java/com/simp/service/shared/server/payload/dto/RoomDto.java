package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Room;

public record RoomDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("hospital")
        long hospital
) implements Room {
}
