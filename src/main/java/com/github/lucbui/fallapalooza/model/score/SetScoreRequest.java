package com.github.lucbui.fallapalooza.model.score;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SetScoreRequest {
    @NotNull
    private Long teamMemberId;

    @NotNull
    private Long matchupId;

    @Min(0)
    private Integer episode;

    @Min(0)
    private Integer score;
}
