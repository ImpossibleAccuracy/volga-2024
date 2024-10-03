package com.simp.service.shared.server.payload.doctor;

import com.simp.service.shared.server.payload.shared.PaginationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorsFilterRequest extends PaginationRequest {
    public final String nameFilter;
}
