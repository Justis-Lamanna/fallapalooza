package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchupRepository extends JpaRepository<Matchup, Long> {
    /**
     * Retrieve all matchups in a round
     * @param roundId The round ID
     * @return The matchups for that round
     */
    List<Matchup> getMatchupByRoundId(long roundId);

    /**
     * Get all matchups for a tournament
     * @param id The tournament ID
     * @return The matchups for that tournament
     */
    List<Matchup> getMatchupByRoundTournamentId(long id);
}
