package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Round;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.model.tournament.SimpleCreateTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.UpdateTournamentRequest;
import com.github.lucbui.fallapalooza.repository.RoundRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RoundRepository roundRepository;

    /**
     * Create a standard tournament, given a request
     * This also creates 5 rounds w/ appropriate names
     * @param request The tournament request
     * @return The created Tournament
     */
    @Transactional
    public Tournament createStandard(SimpleCreateTournamentRequest request) {
        Tournament t = new Tournament(request.getName());
        t.setStartDate(request.getStartDate());
        t.setSignUpStartDate(request.getSignUpStartDate());
        t.setSignUpEndDate(request.getSignUpEndDate());
        Tournament fT = tournamentRepository.save(t);

        List<Round> rounds = IntStream.range(0, 5)
                .mapToObj(i -> {
                    Round r = new Round(i, getRoundByIndex(i), fT);
                    if(i == 4) {
                        r.setFinalRound(true);
                    }
                    return r;
                })
                .collect(Collectors.toList());

        roundRepository.saveAll(rounds);

        return t;
    }

    private String getRoundByIndex(int i) {
        switch(i + 1) {
            case 1: return "Round 1";
            case 2: return "Round 2";
            case 3: return "Quarterfinals";
            case 4: return "Semifinals";
            case 5: return "Finals";
            default: throw new IllegalArgumentException(Integer.toString(i));
        }
    }

    /**
     * Create a Tournament, given a request
     * @param request The tournament request
     * @return The created Tournament
     */
    public Tournament update(UpdateTournamentRequest request) {
        Tournament t = tournamentRepository.findById(request.getId())
                .orElseThrow(() -> new TournamentNotFoundException(request.getId()));
        if(request.getName() != null) { t.setName(request.getName()); }
        if(request.getSignUpStartDate() != null) { t.setSignUpStartDate(request.getSignUpStartDate()); }
        if(request.getSignUpEndDate() != null) { t.setSignUpEndDate(request.getSignUpEndDate()); }
        if(request.getStartDate() != null) { t.setStartDate(request.getStartDate()); }
        if(request.getEndDate() != null) { t.setEndDate(request.getEndDate()); }
        return tournamentRepository.save(t);
    }

    /**
     * Get a tournament by its ID
     * @param id The tournament ID
     * @return The tournament
     */
    public Tournament getTournamentById(long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    /**
     * Get all tournaments
     * @return All tournaments
     */
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    /**
     * Get current tournament
     * @return The current tournament
     */
    public Tournament getCurrentTournament() {
        return tournamentRepository.getCurrentTournament()
                .orElseThrow(TournamentNotFoundException::new);
    }
}
