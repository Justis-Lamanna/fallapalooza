package com.github.lucbui.fallapalooza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoundNotFoundException extends RuntimeException {
    public RoundNotFoundException(long id) {
        super("Round not found: " + id);
    }
}
