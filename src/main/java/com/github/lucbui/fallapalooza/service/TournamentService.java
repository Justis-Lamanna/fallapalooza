package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Round;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.model.tournament.CreateStandardTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.CreateTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.DeleteTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.UpdateTournamentRequest;
import com.github.lucbui.fallapalooza.repository.RoundRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RoundRepository roundRepository;

    /**
     * Create a Tournament, given a request
     * @param request The tournament request
     * @return The created Tournament
     */
    public Tournament create(CreateTournamentRequest request) {
        Tournament t = new Tournament(request.getName());
        t.setSignUpStartDate(request.getSignUpStartDate());
        t.setSignUpEndDate(request.getSignUpEndDate());
        t.setStartDate(request.getStartDate());
        t.setEndDate(request.getEndDate());
        return tournamentRepository.save(t);
    }

    /**
     * Create a standard tournament, given a request
     * This also creates 5 rounds w/ appropriate names
     * @param request The tournament request
     * @return The created Tournament
     */
    public Tournament createStandard(CreateStandardTournamentRequest request) {
        Tournament t = new Tournament(request.getName());
        t.setSignUpStartDate(request.getSignUpStartDate());
        t.setSignUpEndDate(request.getSignUpEndDate());
        t.setStartDate(request.getStartDate());
        t.setEndDate(request.getEndDate());
        t = tournamentRepository.save(t);

        for(int i = 0; i < 5; i++) {
            roundRepository.save(new Round(i, getRoundByIndex(i), t));
        }

        return t;
    }

    private String getRoundByIndex(int i) {
        switch(i) {
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
        t.setName(request.getName());
        t.setSignUpStartDate(request.getSignUpStartDate());
        t.setSignUpEndDate(request.getSignUpEndDate());
        t.setStartDate(request.getStartDate());
        t.setEndDate(request.getEndDate());
        return tournamentRepository.save(t);
    }

    public void delete(DeleteTournamentRequest request) {
        tournamentRepository.deleteById(request.getId());
    }
}
