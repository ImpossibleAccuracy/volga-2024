package com.simp.service.shared.server.payload.shared;

import java.util.Date;

public record DatePaginationRequest(Date from, Date to) {
}
