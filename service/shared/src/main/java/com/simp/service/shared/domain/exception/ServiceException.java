package com.simp.service.shared.domain.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ServiceException extends ResponseStatusException {
    public ServiceException(String message, int status) {
        super(HttpStatusCode.valueOf(status), message);
    }
}
