package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.model.Bracket;
import com.github.lucbui.fallapalooza.service.MatchupService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bracket")
public class BracketController {
    @Autowired
    private MatchupService matchupService;

    @GetMapping("/{id}")
    @ApiOperation("Get the bracket for a tournament")
    public Bracket getBracketForTournament(@PathVariable @ApiParam("${api.tournament.id}") long id) {
        return matchupService.getBracketForTournament(id);
    }

    @GetMapping("/{id}/round/{round}")
    @ApiOperation("Get the bracket for a tournament")
    public List<Bracket> getSubBracketsForRound(@PathVariable @ApiParam("${api.tournament.id}") long id, @PathVariable int round) {
        return matchupService.getBracketsForTournament(id, round);
    }
}
