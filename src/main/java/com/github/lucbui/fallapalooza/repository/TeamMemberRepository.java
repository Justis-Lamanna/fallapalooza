package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    /**
     * Get a team member by the team and player IDs
     * @param teamId The team ID
     * @param playerId The player ID
     * @return The member, if found
     */
    Optional<TeamMember> getTeamMemberByTeamIdAndPlayerId(long teamId, long playerId);

    /**
     * Get a team member by the tournament and player ID
     * @param tournamentId The tournament ID
     * @param playerId The player ID
     * @return The member, if found
     */
    Optional<TeamMember> getTeamMemberByTeamTournamentIdAndPlayerId(long tournamentId, long playerId);

    /**
     * Get a team member by the tournament and player ID
     * @param tournamentId The tournament ID
     * @param discordId The player's Discord ID
     * @return The member, if found
     */
    Optional<TeamMember> getTeamMemberByTeamTournamentIdAndPlayerDiscordId(long tournamentId, String discordId);

    /**
     * Get a team member by the tournament and player ID
     * @param tournamentId The tournament ID
     * @param twitterId The player's Twitter ID
     * @return The member, if found
     */
    Optional<TeamMember> getTeamMemberByTeamTournamentIdAndPlayerTwitterId(long tournamentId, String twitterId);

    /**
     * Get a team member by the tournament and player ID
     * @param tournamentId The tournament ID
     * @param twitchId The player's Twitch ID
     * @return The member, if found
     */
    Optional<TeamMember> getTeamMemberByTeamTournamentIdAndPlayerTwitchId(long tournamentId, String twitchId);

    /**
     * Get all team members in a team
     * @param id The Team ID
     * @return The members in that team
     */
    List<TeamMember> getTeamMemberByTeamId(long id);

    /**
     * Get all teams a user is in
     * @param id The User ID
     * @return The team members they are a part of
     */
    List<TeamMember> getTeamMemberByPlayerId(long id);
}
