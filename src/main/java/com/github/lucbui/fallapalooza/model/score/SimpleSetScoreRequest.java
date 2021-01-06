package com.github.lucbui.fallapalooza.model.score;

import com.github.lucbui.fallapalooza.model.team.IdType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SimpleSetScoreRequest {
    @NotNull
    private Long tournamentId;

    @NotNull
    private String userId;

    @NotNull
    private IdType idType;

    @NotNull
    private Integer round;

    @NotNull
    private Integer episode;

    @NotNull
    private Integer score;
}
