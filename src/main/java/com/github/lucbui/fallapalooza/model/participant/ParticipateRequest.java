package com.github.lucbui.fallapalooza.model.participant;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParticipateRequest {
    @NotNull
    private Long tournamentId;

    @NotNull
    private Long userId;
}
