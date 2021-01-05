package com.github.lucbui.fallapalooza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(long id) {
        super("No tournament found: " + id);
    }

    public TournamentNotFoundException() {
        super("No current tournament found");
    }
}
