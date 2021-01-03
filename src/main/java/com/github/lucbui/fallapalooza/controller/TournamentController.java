package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.model.tournament.QuickCreateTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.UpdateTournamentRequest;
import com.github.lucbui.fallapalooza.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable long id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping
    public Tournament createStandardTournament(@Valid QuickCreateTournamentRequest request) {
        return tournamentService.createStandard(request);
    }

    @PutMapping
    public Tournament updateTournament(@Valid @RequestBody UpdateTournamentRequest request) {
        return tournamentService.update(request);
    }
}
