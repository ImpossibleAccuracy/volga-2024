package com.simp.service.shared.domain.model;

public record Pagination(
        int from,
        int count
) {
    public static final int defaultFrom = 0;
    public static final int defaultCount = 50;
}
