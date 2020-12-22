package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Round;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoundRepository extends CrudRepository<Round, Long> {
    /**
     * Retrieve all rounds for a given Tournament ID
     * @param id The tournament ID
     * @return The rounds of that tournament
     */
    List<Round> getRoundByTournamentId(long id);
}
