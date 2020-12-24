package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

@Data
public class RemoveTeamMemberRequest {
    private long teamId;
    private long userId;
}
