package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

@Data
public class UpdateTeamRequest {
    private long teamId;
    private String name;
    private String color;
    private Integer seed;
}
