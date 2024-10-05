package com.simp.service.shared.domain.exception;

// TODO: add exception handler
public class ServiceException extends RuntimeException {
    public final int status;

    public ServiceException(String message, int status) {
        super(message);
        this.status = status;
    }
}
