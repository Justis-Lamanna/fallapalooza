package com.github.lucbui.fallapalooza.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateUserRequest {
    @NotNull
    @ApiModelProperty(value = "${api.user.id}", required = true)
    private Long id;

    @ApiModelProperty("${api.user.name}")
    private String name;

    @ApiModelProperty("${api.user.pronouns}")
    private String pronouns;

    @ApiModelProperty("${api.user.blurb}")
    private String blurb;

    @PositiveOrZero(message = "Crown Count must be non-negative")
    @ApiModelProperty("${api.user.crownCount}")
    private Integer crownCount;

    @ApiModelProperty("${api.user.twitterId}")
    private String twitterId;
}
