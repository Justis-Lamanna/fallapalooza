package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

import java.util.List;

@Data
public class CreateTeamRequest {
    private long tournamentId;
    private String name;
    private String color;
    private Integer seed;
    private List<CreateMemberRequest> members;

    @Data
    public static class CreateMemberRequest {
        private long id;
        private boolean backup;
    }
}
