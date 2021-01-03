package com.github.lucbui.fallapalooza.model.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long id;

    private String name;

    private String pronouns;

    private String blurb;

    @PositiveOrZero(message = "Crown Count must be non-negative")
    private Integer crownCount;

    private String twitterId;
}
