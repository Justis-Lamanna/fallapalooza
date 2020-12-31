package com.github.lucbui.fallapalooza.model.tournament;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Data
public class QuickCreateTournamentRequest {
    @NotBlank(message = "Must provide Tournament name")
    private String name;

    private OffsetDateTime signUpStartDate;

    private OffsetDateTime signUpEndDate;

    private OffsetDateTime startDate;
}
