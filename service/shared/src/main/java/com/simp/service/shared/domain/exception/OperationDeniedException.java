package com.simp.service.shared.domain.exception;

public class OperationDeniedException extends ServiceException {
    public OperationDeniedException(String message) {
        super(message, 403);
    }
}
