package com.github.lucbui.fallapalooza.model.matchup;

import com.github.lucbui.fallapalooza.entity.Matchup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class UpdateMatchupRequest {
    @NotNull
    @ApiModelProperty(value = "${api.matchup.id}", required = true)
    private Long matchupId;

    @ApiModelProperty("${api.matchup.matchupOrder}")
    private Integer matchupOrder;

    @ApiModelProperty(value = "${api.matchup.winner}")
    private Matchup.Winner winner;

    @ApiModelProperty("${api.matchup.startDate}")
    private OffsetDateTime startDate;

    @ApiModelProperty("${api.matchup.endDate}")
    private OffsetDateTime endDate;
}
