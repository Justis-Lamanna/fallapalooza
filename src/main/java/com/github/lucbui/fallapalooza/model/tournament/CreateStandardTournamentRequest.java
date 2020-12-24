package com.github.lucbui.fallapalooza.model.tournament;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateStandardTournamentRequest {
    private String name;
    private OffsetDateTime signUpStartDate;
    private OffsetDateTime signUpEndDate;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}