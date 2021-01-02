package com.github.lucbui.fallapalooza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MisconfiguredTournamentException extends RuntimeException {
    public MisconfiguredTournamentException() {
    }

    public MisconfiguredTournamentException(String message) {
        super(message);
    }

    public MisconfiguredTournamentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MisconfiguredTournamentException(Throwable cause) {
        super(cause);
    }
}
