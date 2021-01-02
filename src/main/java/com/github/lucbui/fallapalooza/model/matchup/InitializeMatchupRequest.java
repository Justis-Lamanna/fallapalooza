package com.github.lucbui.fallapalooza.model.matchup;

import lombok.Data;

@Data
public class InitializeMatchupRequest {
    private Long tournamentId;
    private Seeds seeds;
}
