package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Matchup;
import com.github.lucbui.fallapalooza.model.Bracket;
import com.github.lucbui.fallapalooza.model.matchup.InitializeMatchupRequest;
import com.github.lucbui.fallapalooza.model.matchup.UpdateMatchupRequest;
import com.github.lucbui.fallapalooza.service.MatchupService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matchup")
public class MatchupController {
    @Autowired
    private MatchupService matchupService;

    @PostMapping
    @ApiOperation("Create tournament matchups from a group of seeds")
    public List<Matchup> createMatchups(@Valid @RequestBody InitializeMatchupRequest request) {
        return matchupService.initializeMatchups(request);
    }

    @PutMapping
    @ApiOperation("Mark a matchup with a winner")
    public Matchup updateMatchup(@Valid @RequestBody UpdateMatchupRequest request) {
        return matchupService.updateMatchup(request);
    }

    @GetMapping("/bracket/{id}")
    @ApiOperation("Get the bracket for a tournament")
    public Bracket getMatchupsForTournament(@PathVariable @ApiParam("${api.tournament.id}") long id) {
        return matchupService.getBracketForTournament(id);
    }
}
