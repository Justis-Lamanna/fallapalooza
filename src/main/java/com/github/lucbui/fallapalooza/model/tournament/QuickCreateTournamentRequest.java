package com.github.lucbui.fallapalooza.model.tournament;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Data
public class QuickCreateTournamentRequest {
    @NotBlank(message = "Must provide Tournament name")
    @ApiModelProperty(value = "${api.tournament.name}", required = true)
    private String name;

    @ApiModelProperty("${api.tournament.signUpStartDate}")
    private OffsetDateTime signUpStartDate;

    @ApiModelProperty("${api.tournament.signUpEndDate}")
    private OffsetDateTime signUpEndDate;

    @ApiModelProperty("${api.tournament.startDate}")
    private OffsetDateTime startDate;
}
