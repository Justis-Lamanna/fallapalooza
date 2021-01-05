package com.github.lucbui.fallapalooza.model.tournament;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class UpdateTournamentRequest {
    @NotNull
    @ApiModelProperty(value = "${api.tournament.startDate}", required = true)
    private Long id;

    @ApiModelProperty("${api.tournament.name}")
    private String name;

    @ApiModelProperty("${api.tournament.signUpStartDate}")
    private OffsetDateTime signUpStartDate;

    @ApiModelProperty("${api.tournament.signUpEndDate}")
    private OffsetDateTime signUpEndDate;

    @ApiModelProperty("${api.tournament.startDate}")
    private OffsetDateTime startDate;

    @ApiModelProperty("${api.tournament.endDate}")
    private OffsetDateTime endDate;
}
