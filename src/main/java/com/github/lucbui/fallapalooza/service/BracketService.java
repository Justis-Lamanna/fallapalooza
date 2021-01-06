package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.entity.Score;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.model.Bracket;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BracketService {
    public Bracket matchupsToBracket(Matchup matchup) {
        Bracket bracket = new Bracket();
        Bracket.Match match = new Bracket.Match();
        match.setMatchupId(matchup.getId());
        bracket.setMatch(match);

        //Recursively populate the bracket
        if(matchup.getTeamOne() == null) {
            Bracket subBracket = matchupsToBracket(matchup.getPreviousMatchupTeamOne());
            match.setTeamOne(subBracket.getWinner());
            match.setTeamOnePreviousMatch(subBracket.getMatch());
            bracket.getTeams().putAll(subBracket.getTeams());
        } else {
            match.setTeamOne(matchup.getTeamOne().getId());
            bracket.getTeams().put(matchup.getTeamOne().getId(), matchup.getTeamOne());
        }

        if(matchup.getTeamTwo() == null) {
            Bracket subBracket = matchupsToBracket(matchup.getPreviousMatchupTeamTwo());
            match.setTeamTwo(subBracket.getWinner());
            match.setTeamTwoPreviousMatch(subBracket.getMatch());
            bracket.getTeams().putAll(subBracket.getTeams());
        } else {
            match.setTeamTwo(matchup.getTeamTwo().getId());
            bracket.getTeams().put(matchup.getTeamTwo().getId(), matchup.getTeamTwo());
        }

        //Populate scores
        Team teamOne = bracket.getTeams().get(match.getTeamOne());
        List<Long> teamOneIds = teamOne == null ?
                Collections.emptyList() :
                teamOne.getMembers().stream().map(TeamMember::getId).collect(Collectors.toList());
        Team teamTwo = bracket.getTeams().get(match.getTeamTwo());
        List<Long> teamTwoIds = teamTwo == null ?
                Collections.emptyList() :
                teamTwo.getMembers().stream().map(TeamMember::getId).collect(Collectors.toList());

        matchup.getScores().stream()
                .filter(score -> teamOneIds.contains(score.getTeamMember().getId()) || teamTwoIds.contains(score.getTeamMember().getId()))
                .sorted(Comparator.comparing(Score::getScore))
                .forEach(score -> {
                    long memberId = score.getTeamMember().getId();
                    (teamOneIds.contains(memberId) ? match.getTeamOneScores() : match.getTeamTwoScores())
                            .computeIfAbsent(memberId, l -> new HashMap<>()).put(score.getEpisode(), score.getScore());
                });

        //Finally, set the winner
        switch (matchup.getWinner()) {
            case TEAM_ONE: bracket.setWinner(bracket.getMatch().getTeamOne()); break;
            case TEAM_TWO: bracket.setWinner(bracket.getMatch().getTeamTwo()); break;
        }

        return bracket;
    }
}
