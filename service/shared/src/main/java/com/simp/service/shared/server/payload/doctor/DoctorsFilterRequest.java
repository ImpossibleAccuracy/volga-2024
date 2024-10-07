package com.simp.service.shared.server.payload.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.server.payload.shared.PaginationRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorsFilterRequest extends PaginationRequest {
    @NotBlank
    @JsonProperty("nameFilter")
    public final String nameFilter;
}
