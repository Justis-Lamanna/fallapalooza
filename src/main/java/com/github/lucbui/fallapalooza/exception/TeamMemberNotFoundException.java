package com.github.lucbui.fallapalooza.exception;

import com.github.lucbui.fallapalooza.model.team.IdType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamMemberNotFoundException extends RuntimeException {
    public TeamMemberNotFoundException(long id) {
        super("Member not found: " + id);
    }

    public TeamMemberNotFoundException(IdType idType, String id) {
        super("Member not found (" + idType + "): " + id);
    }
}
