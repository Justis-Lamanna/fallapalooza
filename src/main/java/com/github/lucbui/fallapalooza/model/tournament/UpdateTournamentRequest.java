package com.github.lucbui.fallapalooza.model.tournament;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTournamentRequest {
    private long id;
    private String name;
    private OffsetDateTime signUpStartDate;
    private OffsetDateTime signUpEndDate;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
