package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
     * Get a matchup given the competing teams
     * Note that the teams can be swapped.
     * @param teamOneId The ID of team one
     * @param teamTwoId The ID of team two
     * @return The matchup, if it exists
     */
    @Query("SELECT m " +
            "FROM Matchup m " +
            "JOIN m.teamOne t1 " +
            "JOIN m.teamTwo t2 " +
            "WHERE (t1.id = :teamOneId AND t2.id = :teamTwoId) " +
            "OR (t1.id = :teamTwoId AND t2.id = :teamOneId)")
    Optional<Matchup> getMatchupByTeamOneIdAndTeamTwoId(long teamOneId, long teamTwoId);
}
