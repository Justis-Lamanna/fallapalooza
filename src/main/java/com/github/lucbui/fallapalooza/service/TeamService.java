package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    /**
     * Get a team by its ID
     * @param id The Team ID
     * @return The team itself
     */
    public Optional<Team> getTeam(long id) {
        return repository.findById(id);
    }

    /**
     * Get team by tournament seed
     * @param tournamentId The tournament ID
     * @param seed The tournament seed
     * @return The team in that bracket
     */
    public Optional<Team> getTeamByTournamentIdAndSeed(long tournamentId, int seed) {
        return repository.getTeamByTournamentIdAndSeed(tournamentId, seed);
    }

    /**
     * Get teams in a tournament
     * @param tournamentId The tournament ID
     * @return The teams in the tournament
     */
    public List<Team> getTeamsInTournament(long tournamentId) {
        return repository.getTeamByTournamentId(tournamentId);
    }

    /**
     * Get all teams using a pageable
     * @param pageable The page to get
     * @return The teams on that page
     */
    public Iterable<Team> getTeams(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Create/update team
     * @param team The team to create or update
     * @return The team, as saved in the database
     */
    public Team createOrUpdateTeam(Team team) {
        return repository.save(team);
    }

    /**
     * Delete a team
     * @param id The team ID
     */
    public void deleteTeam(long id) {
        repository.deleteById(id);
    }
}
