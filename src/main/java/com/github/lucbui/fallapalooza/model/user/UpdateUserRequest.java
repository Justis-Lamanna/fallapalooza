package com.github.lucbui.fallapalooza.model.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private long id;
    private String name;
    private String pronouns;
    private String discordId;
}
