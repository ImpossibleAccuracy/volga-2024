package com.simp.service.shared.domain.exception;

public class ServiceException extends RuntimeException {
    public final int status;

    public ServiceException(String message, int status) {
        super(message);
        this.status = status;
    }
}
