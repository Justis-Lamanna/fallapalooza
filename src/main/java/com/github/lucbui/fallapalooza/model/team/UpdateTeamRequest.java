package com.github.lucbui.fallapalooza.model.team;

import com.github.lucbui.fallapalooza.entity.Region;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateTeamRequest {
    @NotNull
    @ApiModelProperty(value = "${api.team.id}", required = true)
    private Long teamId;

    @ApiModelProperty("${api.team.name}")
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    @ApiModelProperty("${api.team.color}")
    private String color;

    @ApiModelProperty("${api.team.seed}")
    private Integer seed;

    @ApiModelProperty("${api.team.preferredRegion}")
    private Region preferredRegion;
}
