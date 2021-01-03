package com.github.lucbui.fallapalooza.model.tournament;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class UpdateTournamentRequest {
    @NotNull
    private Long id;

    private String name;

    private OffsetDateTime signUpStartDate;

    private OffsetDateTime signUpEndDate;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;
}
