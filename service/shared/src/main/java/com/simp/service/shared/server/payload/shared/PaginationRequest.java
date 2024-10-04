package com.simp.service.shared.server.payload.shared;

import com.simp.service.shared.domain.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    public int from = Pagination.defaultFrom;
    public int count = Pagination.defaultCount;
}
