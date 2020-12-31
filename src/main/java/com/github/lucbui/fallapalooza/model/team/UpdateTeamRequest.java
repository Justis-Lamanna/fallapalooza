package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateTeamRequest {
    private long teamId;

    @NotBlank(message = "Must provide Team Name")
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    private String color;

    private Integer seed;
}
