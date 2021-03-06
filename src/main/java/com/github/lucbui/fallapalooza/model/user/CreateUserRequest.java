package com.github.lucbui.fallapalooza.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CreateUserRequest {
    @NotBlank(message = "User name must be provided")
    @ApiModelProperty(value = "${api.user.name}", required = true)
    private String name;

    @ApiModelProperty("${api.user.pronouns}")
    private String pronouns;

    @ApiModelProperty("${api.user.blurb}")
    private String blurb;

    @PositiveOrZero(message = "Crown Count must be non-negative")
    @ApiModelProperty("${api.user.crownCount}")
    private Integer crownCount;

    @NotBlank(message = "Discord ID must be provided")
    @ApiModelProperty(value = "${api.user.discordId}", required = true)
    private String discordId;

    @NotBlank(message = "Twitch ID must be provided")
    @ApiModelProperty(value = "${api.user.twitchId}", required = true)
    private String twitchId;

    @ApiModelProperty("${api.user.twitterId}")
    private String twitterId;
}
