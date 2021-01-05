package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.entity.Round;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.exception.MatchupNotFoundException;
import com.github.lucbui.fallapalooza.exception.MisconfiguredTournamentException;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.model.matchup.InitializeMatchupRequest;
import com.github.lucbui.fallapalooza.model.matchup.Seeds;
import com.github.lucbui.fallapalooza.model.matchup.UpdateMatchupRequest;
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
import java.util.stream.IntStream;

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
        List<Round> rounds = getRounds(request.getTournamentId());

        List<Matchup> created = new ArrayList<>(2 << rounds.size());

        int index = 0;
        for(Seeds.Seed seed : request.getSeeds()) {
            Team one = teamRepository.getTeamByTournamentIdAndSeed(request.getTournamentId(), seed.getTeamOne())
                    .orElseThrow(() -> new TeamNotFoundException(seed.getTeamOne()));
            Team two = teamRepository.getTeamByTournamentIdAndSeed(request.getTournamentId(), seed.getTeamTwo())
                    .orElseThrow(() -> new TeamNotFoundException(seed.getTeamOne()));
            Matchup matchup = new Matchup(rounds.get(0));
            matchup.setTeamOne(one); matchup.setTeamTwo(two); matchup.setMatchupOrder(index++);
            created.add(matchup);
        }

        List<Matchup> previous = created;
        for(int idx = 1; idx < rounds.size(); idx++) {
            previous = createMatchupForRound(rounds.get(idx), previous);
            created.addAll(previous);
        }

        return matchupRepository.saveAll(created);
    }

    private List<Round> getRounds(long tournamentId) {
        List<Round> rounds = roundRepository.getRoundByTournamentId(tournamentId).stream()
                .sorted(Comparator.comparing(Round::getNumber))
                .collect(Collectors.toList());
        if(rounds.isEmpty()) {
            throw new MisconfiguredTournamentException("Tournament with ID " + tournamentId + " has no rounds set up.");
        }
        return rounds;
    }

    private List<Matchup> createMatchupForRound(Round round, List<Matchup> previous) {
        int numberOfMatchups = 1 << (4 - round.getNumber());
        return IntStream.range(0, numberOfMatchups)
            .mapToObj(matchupNum -> {
                Matchup matchup = new Matchup(round);
                matchup.setMatchupOrder(matchupNum);
                matchup.setPreviousMatchupTeamOne(previous.get(2 * matchupNum));
                matchup.setPreviousMatchupTeamTwo(previous.get(2 * matchupNum + 1));
                return matchup;
            })
            .collect(Collectors.toList());
    }

    public Matchup updateMatchup(UpdateMatchupRequest request) {
        Matchup matchup = matchupRepository.findById(request.getMatchupId())
                .orElseThrow(() -> new MatchupNotFoundException(request.getMatchupId()));
        if ( request.getMatchupOrder() != null) { matchup.setMatchupOrder(request.getMatchupOrder());}
        if ( request.getWinner() != null ) { matchup.setWinner(request.getWinner()); }
        if ( request.getStartDate() != null) { matchup.setStartDate(request.getStartDate()); }
        if (request.getEndDate() != null) { matchup.setEndDate(request.getEndDate()); }
        return matchupRepository.save(matchup);
    }

    public List<Matchup> getMatchups() {
        return matchupRepository.findAll();
    }

    public List<Matchup> getMatchupsForTournament(long id) {
        return matchupRepository.getMatchupByRoundTournamentId(id);
    }

    public List<Matchup> getMatchupsForRound(long id) {
        return matchupRepository.getMatchupByRoundId(id);
    }
}
