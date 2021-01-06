package com.github.lucbui.fallapalooza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchupNotFoundException extends RuntimeException {
    public MatchupNotFoundException(long id) {
        super("No matchup found: " + id);
    }

    public MatchupNotFoundException() {
        super();
    }
}
