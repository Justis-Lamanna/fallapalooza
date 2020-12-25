package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    /**
     * Retrieve all teams in a tournament
     * @param id The tournament ID
     * @return The teams in the tournament
     */
    List<Team> getTeamByTournamentId(long id);

    /**
     * Retrieve all active (backup=false) teams
     * @param id The tournament ID
     * @param backup True to retrieve backups, false for actives
     * @return The active teams
     */
    Long countTeamsByTournamentIdAndBackup(long id, boolean backup);

    /**
     * Get a team by its seed #
     * @param tournamentId The tournament ID
     * @param seed The seed number
     * @return The corresponding team
     */
    Optional<Team> getTeamByTournamentIdAndSeed(long tournamentId, int seed);

    
}
