package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

import java.util.List;

@Data
public class CreateTeamAndUserRequest {
    private long tournamentId;
    private String name;
    private String color;
    private List<DiscordUser> discordUsers;

    @Data
    public static class DiscordUser {
        private String discordId;
        private String twitchId;
        private String name;
        private String pronouns;
        private String blurb;
        private boolean backup;
    }
}
