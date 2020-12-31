package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    /**
     * Get a single score given a round, member, and episode
     * @param matchupId The match ID
     * @param memberId The member ID
     * @param episode The episode ID
     * @return The score, if present
     */
    Optional<Score> getScoreByMatchupIdAndTeamMemberIdAndEpisode(long matchupId, long memberId, int episode);

    /**
     * Get the scores for a given round and member
     * @param matchupId The match ID
     * @param memberId The member ID
     * @return All scores for that round
     */
    List<Score> getScoreByMatchupIdAndTeamMemberId(long matchupId, long memberId);

    /**
     * Get the scores for everyone in this round
     * @param matchupId The match ID
     * @return All scores for that round
     */
    List<Score> getScoreByMatchupId(long matchupId);

    /**
     * Get the scores for this entire team in this entire round
     * @param matchupId The match ID
     * @param teamId The team ID
     * @return All scores from this round for this team
     */
    List<Score> getScoreByMatchupIdAndTeamMemberTeamId(long matchupId, long teamId);
}
