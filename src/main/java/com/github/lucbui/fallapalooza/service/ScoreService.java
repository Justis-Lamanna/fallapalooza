package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.entity.Score;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.exception.MatchupNotFoundException;
import com.github.lucbui.fallapalooza.exception.TeamMemberNotFoundException;
import com.github.lucbui.fallapalooza.model.Bracket;
import com.github.lucbui.fallapalooza.model.score.SimpleSetScoreRequest;
import com.github.lucbui.fallapalooza.model.team.IdType;
import com.github.lucbui.fallapalooza.repository.MatchupRepository;
import com.github.lucbui.fallapalooza.repository.RoundRepository;
import com.github.lucbui.fallapalooza.repository.ScoreRepository;
import com.github.lucbui.fallapalooza.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private MatchupRepository matchupRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private BracketService bracketService;

    public Score setOrUpdateScore(SimpleSetScoreRequest request) {
        TeamMember member = getTeamMemberByIdType(request.getIdType(), request.getTournamentId(), request.getUserId())
                .orElseThrow(() -> new TeamMemberNotFoundException(request.getIdType(), request.getUserId()));
        Team team = member.getTeam();

        Matchup matchup = getMatchup(request.getTournamentId(), request.getRound(), team);

        Score score = scoreRepository.getScoreByMatchupIdAndTeamMemberIdAndEpisode(matchup.getId(), member.getId(), request.getEpisode())
                .map(s -> {
                    s.setScore(request.getScore());
                    return scoreRepository.save(s);
                })
                .orElseGet(() -> new Score(matchup, member, request.getEpisode(), request.getScore()));

        return scoreRepository.save(score);
    }

    private Matchup getMatchup(long tournamentId, int roundNumber, Team team) {
        return matchupRepository.getMatchupByRoundTournamentIdAndRoundNumber(tournamentId, roundNumber)
                    .stream()
                    .map(bracketService::matchupsToBracket)
                    .filter(bracket -> {
                        Bracket.Match match = bracket.getMatch();
                        if(match.getTeamOne() != null && match.getTeamOne().longValue() == team.getId().longValue()) {
                            return true;
                        }
                        return match.getTeamTwo() != null && match.getTeamTwo().longValue() == team.getId().longValue();
                    })
                    .findFirst()
                    .map(bracket -> matchupRepository.getOne(bracket.getMatch().getMatchupId()))
                    .orElseThrow(MatchupNotFoundException::new);
    }

    private Optional<TeamMember> getTeamMemberByIdType(IdType idType, long tournamentId, String userId) {
        switch (idType) {
            case TWITCH: return teamMemberRepository.getTeamMemberByTeamTournamentIdAndPlayerTwitchId(tournamentId, userId);
            case TWITTER: return teamMemberRepository.getTeamMemberByTeamTournamentIdAndPlayerTwitterId(tournamentId, userId);
            case DISCORD: return teamMemberRepository.getTeamMemberByTeamTournamentIdAndPlayerDiscordId(tournamentId, userId);
            default: return Optional.empty();
        }
    }
}
