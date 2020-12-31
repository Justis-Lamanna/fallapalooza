package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchupRepository extends JpaRepository<Matchup, Long> {
    List<Matchup> getMatchupByRoundId(long roundId);
}
