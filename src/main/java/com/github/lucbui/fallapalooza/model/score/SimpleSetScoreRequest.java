package com.github.lucbui.fallapalooza.model.score;

import com.github.lucbui.fallapalooza.model.team.IdType;
import lombok.Data;

@Data
public class SimpleSetScoreRequest {
    private Long tournamentId;
    private String userId;
    private IdType idType;
    private Integer round;
    private Integer episode;
    private Integer score;
}
