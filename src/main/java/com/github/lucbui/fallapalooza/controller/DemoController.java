package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournament")
public class DemoController {
    @Autowired
    private TournamentRepository repository;

    @GetMapping
    public Iterable<Tournament> getAllTournaments() {
        return repository.findAll();
    }
}
