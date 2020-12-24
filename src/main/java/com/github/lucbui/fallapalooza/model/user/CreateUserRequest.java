package com.github.lucbui.fallapalooza.model.user;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String pronouns;
    private String discordId;
    private String twitchId;
}
