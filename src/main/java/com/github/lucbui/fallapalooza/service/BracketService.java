package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.model.Bracket;
import org.springframework.stereotype.Service;

@Service
public class BracketService {
    public Bracket matchupsToBracket(Matchup matchup) {
        Bracket bracket = new Bracket();
        Bracket.Match match = new Bracket.Match();
        bracket.setMatch(match);

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

        switch (matchup.getWinner()) {
            case TEAM_ONE: bracket.setWinner(bracket.getMatch().getTeamOne()); break;
            case TEAM_TWO: bracket.setWinner(bracket.getMatch().getTeamTwo()); break;
        }

        return bracket;
    }
}
