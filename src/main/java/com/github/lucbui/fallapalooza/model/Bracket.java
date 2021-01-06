package com.github.lucbui.fallapalooza.model;

import com.github.lucbui.fallapalooza.entity.Team;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Bracket {
    private Map<Long, Team> teams = new HashMap<>();
    private Long winner;
    private Match match;

    @Data
    public static class Match {
        private Long teamOne;
        private Long teamTwo;
        private Match teamOnePreviousMatch;
        private Match teamTwoPreviousMatch;
    }
}
