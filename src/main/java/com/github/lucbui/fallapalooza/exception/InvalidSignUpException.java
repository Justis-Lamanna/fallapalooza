package com.github.lucbui.fallapalooza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSignUpException extends RuntimeException {
    public InvalidSignUpException(String message) {
        super(message);
    }
}
