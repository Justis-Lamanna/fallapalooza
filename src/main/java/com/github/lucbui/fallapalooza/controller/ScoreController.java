package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.Score;
import com.github.lucbui.fallapalooza.model.score.SimpleSetScoreRequest;
import com.github.lucbui.fallapalooza.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public Score setScore(@RequestBody @Valid SimpleSetScoreRequest request) {
        return scoreService.setOrUpdateScore(request);
    }
}
