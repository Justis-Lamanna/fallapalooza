package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

@Data
public class AddTeamMemberRequest {
    private long teamId;
    private long userId;
    private boolean backup;
}
