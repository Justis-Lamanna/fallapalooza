package com.github.lucbui.fallapalooza.model.matchup;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InitializeMatchupRequest {
    @NotNull
    @ApiModelProperty(value = "${api.tournament.id}", required = true)
    private Long tournamentId;

    @NotNull
    @ApiModelProperty(required = true)
    private Seeds seeds;
}
