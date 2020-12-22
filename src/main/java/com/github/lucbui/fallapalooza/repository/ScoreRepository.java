package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    /**
     * Get a single score given a round, member, and episode
     * @param roundId The round ID
     * @param memberId The member ID
     * @param episode The episode ID
     * @return The score, if present
     */
    Optional<Score> getScoreByRoundIdAndTeamMemberIdAndEpisode(long roundId, long memberId, int episode);

    /**
     * Get the scores for a given round and member
     * @param roundId The round ID
     * @param memberId The member ID
     * @return All scores for that round
     */
    List<Score> getScoreByRoundIdAndTeamMemberId(long roundId, long memberId);

    /**
     * Get the scores for everyone in this round
     * @param roundId The round ID
     * @return All scores for that round
     */
    List<Score> getScoreByRoundId(long roundId);
}
