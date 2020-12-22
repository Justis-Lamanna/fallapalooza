package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.TeamMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
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
