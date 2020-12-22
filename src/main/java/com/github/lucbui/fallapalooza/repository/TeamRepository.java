package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    /**
     * Retrieve all teams in a tournament
     * @param id The tournament ID
     * @return The teams in the tournament
     */
    List<Team> getTeamByTournamentId(long id);

    /**
     * Get a team by its seed #
     * @param seed The seed number
     * @return The corresponding team
     */
    Optional<Team> getTeamBySeed(long seed);
}
