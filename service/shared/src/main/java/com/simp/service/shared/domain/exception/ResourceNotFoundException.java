package com.simp.service.shared.domain.exception;

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
