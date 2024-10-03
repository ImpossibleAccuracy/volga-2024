package com.simp.service.shared.domain.exception;

public class UnauthorizedException extends ServiceException {
    public UnauthorizedException(String message) {
        super(message, 401);
    }
}
