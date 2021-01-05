package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    /**
     * Get the current tournament
     * Specifically, this is the tournament with no end date, or an end date after query call.
     * In the bizarre edge case of multiples, the one with the earliest start date is returned
     * @return The current tournament
     */
    @Query("SELECT t FROM Tournament t WHERE t.endDate IS NULL OR t.endDate < CURRENT_TIMESTAMP ORDER BY t.startDate")
    Optional<Tournament> getCurrentTournament();
}
