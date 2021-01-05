package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.model.tournament.QuickCreateTournamentRequest;
import com.github.lucbui.fallapalooza.model.tournament.UpdateTournamentRequest;
import com.github.lucbui.fallapalooza.service.TournamentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tournament")
@ApiOperation("Operations relating to Tournament data")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    @ApiOperation("Retrieve a list of all Tournaments")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve a Tournament by its ID")
    public Tournament getTournamentById(@PathVariable @ApiParam("${tournament.id}") long id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping
    @ApiOperation("Create a tournament")
    public Tournament createStandardTournament(@Valid @RequestBody QuickCreateTournamentRequest request) {
        return tournamentService.createStandard(request);
    }

    @PutMapping
    @ApiOperation(value = "Update a tournament", notes = "${api.updateAsPatch}")
    public Tournament updateTournament(@Valid @RequestBody UpdateTournamentRequest request) {
        return tournamentService.update(request);
    }
}
