package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.entity.Round;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.exception.MisconfiguredTournamentException;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.model.matchup.InitializeMatchupRequest;
import com.github.lucbui.fallapalooza.model.matchup.Seeds;
import com.github.lucbui.fallapalooza.repository.MatchupRepository;
import com.github.lucbui.fallapalooza.repository.RoundRepository;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchupService {
    @Autowired
    private MatchupRepository matchupRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Transactional
    public List<Matchup> initializeMatchups(InitializeMatchupRequest request) {
        List<Matchup> created = new ArrayList<>(16);
        Round initialRound = getFirstRoundForTournament(request.getTournamentId());

        int index = 0;
        for(Seeds.Seed seed : request.getSeeds()) {
            Team one = teamRepository.getTeamByTournamentIdAndSeed(request.getTournamentId(), seed.getTeamOne())
                    .orElseThrow(() -> new TeamNotFoundException(seed.getTeamOne()));
            Team two = teamRepository.getTeamByTournamentIdAndSeed(request.getTournamentId(), seed.getTeamTwo())
                    .orElseThrow(() -> new TeamNotFoundException(seed.getTeamOne()));
            Matchup matchup = new Matchup(one, two, initialRound);
            matchup.setMatchupOrder(index++);
            created.add(matchup);
        }

        return matchupRepository.saveAll(created);
    }

    private Round getFirstRoundForTournament(long tournamentId) {
        List<Round> rounds = roundRepository.getRoundByTournamentId(tournamentId).stream()
                .sorted(Comparator.comparing(Round::getNumber))
                .collect(Collectors.toList());
        if(rounds.isEmpty()) {
            throw new MisconfiguredTournamentException("Tournament with ID " + tournamentId + " has no rounds set up.");
        }
        return rounds.get(0);
    }
}
