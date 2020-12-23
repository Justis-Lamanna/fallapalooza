package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that handles tournament retrieval
 * Note: We don't use Pageable here because there shouldn't be **that** many...
 */
@Service
public class TournamentService {
    @Autowired
    private TournamentRepository repository;

    /**
     * Get all tournaments
     * @return The tournaments
     */
    public Iterable<Tournament> getTournaments() {
        return repository.findAll();
    }

    /**
     * Get the current tournament
     * Note that, as of now, this just returns the first one.
     * Will need to include logic at a later date to better retrieve this
     * @return The current tournament, if one is coming up
     */
    public Optional<Tournament> getCurrentTournament() {
        //Stopgap until I figure out a better way to do this.
        return Optional.of(repository.findAll().iterator().next());
    }

    /**
     * Get a tournament by its ID
     * @param id The tournament ID
     * @return The tournament
     * @throws TournamentNotFoundException No such tournament exists
     */
    public Optional<Tournament> getTournamentById(long id) {
        return repository.findById(id);
    }

    /**
     * Upsert a tournament
     * @param tournament The tournament to upsert
     * @return The updated/created tournament
     */
    public Tournament createOrUpdateTournament(Tournament tournament) {
        return repository.save(tournament);
    }

    /**
     * Delete a tournament
     * @param id The ID of the tournament to delete
     */
    public void deleteTournament(long id) {
        repository.deleteById(id);
    }
}
