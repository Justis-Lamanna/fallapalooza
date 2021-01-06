package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Get matchups by tournament and round number
     * @param id The tournament ID
     * @param number The round #
     * @return The matchups for that round
     */
    List<Matchup> getMatchupByRoundTournamentIdAndRoundNumber(long id, int number);

    Optional<Matchup> getMatchupByRoundTournamentIdAndRoundFinalRoundTrue(long tournamentId);
}
