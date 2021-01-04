package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.model.team.CreateTeamAndUserRequest;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.UpdateTeamRequest;
import com.github.lucbui.fallapalooza.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public Page<Team> getUsers(@PageableDefault(20) Pageable pageable) {
        return teamService.getByPageable(pageable);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable long id) {
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
