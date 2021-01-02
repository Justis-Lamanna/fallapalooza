package com.github.lucbui.fallapalooza.exception;

import com.github.lucbui.fallapalooza.model.team.IdType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(long id) {
        super("No user found: " + id);
    }

    public UserNotFoundException(String id, IdType idType) {
        super("No user found: " + id + " of type " + idType);
    }
}
