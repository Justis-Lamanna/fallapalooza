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
        private Long matchupId;
        private Long teamOne;
        private Long teamTwo;
        private Map<Long, Map<Integer, Integer>> teamOneScores = new HashMap<>();
        private Map<Long, Map<Integer, Integer>> teamTwoScores = new HashMap<>();
        private Match teamOnePreviousMatch;
        private Match teamTwoPreviousMatch;

        public Long getTeamOneTotal() {
            if(teamOneScores.size() == 0) {
                return null;
            }
            return teamOneScores.values().stream()
                    .flatMap(c -> c.values().stream())
                    .mapToLong(l -> l)
                    .sum();
        }

        public Long getTeamTwoTotal() {
            if(teamTwoScores.size() == 0) {
                return null;
            }
            return teamTwoScores.values().stream()
                    .flatMap(c -> c.values().stream())
                    .mapToLong(l -> l)
                    .sum();
        }
    }
}
