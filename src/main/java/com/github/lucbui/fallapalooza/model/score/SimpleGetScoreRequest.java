package com.github.lucbui.fallapalooza.model.score;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SimpleGetScoreRequest {
    @NotNull
    private Long tournamentId;

    @NotNull
    private Long teamId;

    @NotNull
    private Long roundId;
}
