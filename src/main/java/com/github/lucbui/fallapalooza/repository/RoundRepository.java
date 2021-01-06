package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoundRepository extends JpaRepository<Round, Long> {
    /**
     * Retrieve all rounds for a given Tournament ID
     * @param id The tournament ID
     * @return The rounds of that tournament
     */
    List<Round> getRoundByTournamentId(long id);

    /**
     * Retrieve a given round for a tournament
     * @param id The tournament ID
     * @param number The round #
     * @return The round, if exists
     */
    Optional<Round> getRoundByTournamentIdAndNumber(long id, long number);

    /**
     * Retrieve the final round for a tournament
     * @param id The tournament ID
     * @return The final round
     */
    Optional<Round> getRoundByTournamentIdAndFinalRoundTrue(long id);
}
