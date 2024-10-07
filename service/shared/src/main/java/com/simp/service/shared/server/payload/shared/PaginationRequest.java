package com.simp.service.shared.server.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Pagination;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    @PositiveOrZero
    @JsonProperty("from")
    public int from = Pagination.defaultFrom;

    @Min(1)
    @JsonProperty("count")
    public int count = Pagination.defaultCount;
}
