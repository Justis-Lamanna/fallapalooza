package com.github.lucbui.fallapalooza.model.team;

import com.github.lucbui.fallapalooza.entity.Region;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateTeamRequest {
    @NotNull
    private Long teamId;

    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    private String color;

    private Integer seed;

    private Region preferredRegion;
}
