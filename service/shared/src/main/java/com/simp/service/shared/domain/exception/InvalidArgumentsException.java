package com.simp.service.shared.domain.exception;

public class InvalidArgumentsException extends ServiceException {
    public InvalidArgumentsException(String message) {
        super(message, 400);
    }
}
