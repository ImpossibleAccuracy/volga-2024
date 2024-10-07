package com.simp.service.shared.server.payload.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenValidateResponse(@JsonProperty("success") boolean success) {
}
