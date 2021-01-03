package com.github.lucbui.fallapalooza.model.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CreateUserRequest {
    @NotBlank(message = "User name must be provided")
    private String name;

    private String pronouns;

    private String blurb;

    @PositiveOrZero(message = "Crown Count must be non-negative")
    private Integer crownCount;

    @NotBlank(message = "Discord ID must be provided")
    private String discordId;

    @NotBlank(message = "Twitch ID must be provided")
    private String twitchId;

    private String twitterId;
}
