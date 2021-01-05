package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.model.team.CreateTeamAndUserRequest;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.UpdateTeamRequest;
import com.github.lucbui.fallapalooza.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
@ApiOperation("Operations relating to Team data")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    @ApiOperation(value = "Retrieve a 'page' of Team data", notes = "${api.pageable}")
    public Page<Team> getTeams(@PageableDefault(20) Pageable pageable) {
        return teamService.getByPageable(pageable);
    }

    @GetMapping("/tournament/{id}")
    @ApiOperation("Retrieve the Teams (both active and backup) in a tournament")
    public List<Team> getTeamsByTournamentId(@PathVariable @ApiParam("${api.tournament.id}") long tournamentId) {
        return teamService.getByTournamentId(tournamentId);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable @ApiParam("${api.team.id}") long id) {
        return teamService.getById(id);
    }

    @PostMapping
    public Team createTeam(@Valid @RequestBody CreateTeamRequest request) {
        return teamService.create(request);
    }

    @PostMapping("/bot")
    public Team createTeamAndUsers(@Valid @RequestBody CreateTeamAndUserRequest request) {
        return teamService.create(request);
    }

    @PutMapping
    public Team updateTeam(@Valid @RequestBody UpdateTeamRequest request) {
        return teamService.update(request);
    }
}
