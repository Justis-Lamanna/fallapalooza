package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.entity.Score;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.model.score.SetScoreRequest;
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

    public Score setOrUpdateScore(SetScoreRequest request) {
        Score s = scoreRepository.getScoreByMatchupIdAndTeamMemberIdAndEpisode(request.getMatchupId(), request.getTeamMemberId(), request.getEpisode())
                .map(score -> {
                    score.setScore(request.getScore());
                    return score;
                })
                .orElseGet(() -> {
                    Matchup matchup = matchupRepository.getOne(request.getMatchupId());
                    TeamMember member = teamMemberRepository.getOne(request.getTeamMemberId());
                    return new Score(matchup, member, request.getEpisode(), request.getScore());
                });
        return scoreRepository.save(s);
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
