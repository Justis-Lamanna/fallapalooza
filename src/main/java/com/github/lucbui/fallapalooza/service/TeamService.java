package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.model.team.AddTeamMemberRequest;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.RemoveTeamMemberRequest;
import com.github.lucbui.fallapalooza.model.team.UpdateTeamRequest;
import com.github.lucbui.fallapalooza.repository.TeamMemberRepository;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a team
     * @param request Team creation request
     * @return The created team
     */
    public Team createTeam(CreateTeamRequest request) {
        Tournament tProxy = tournamentRepository.getOne(request.getTournamentId());
        Team team = new Team(request.getName(), tProxy);
        team.setColor(request.getColor());
        team.setSeed(request.getSeed());
        team = teamRepository.save(team);

        for(CreateTeamRequest.CreateMemberRequest cmRequest : request.getMembers()) {
            TeamMember member = new TeamMember(team, userRepository.getOne(cmRequest.getId()));
            teamMemberRepository.save(member);
        }

        return team;
    }

    /**
     * Update a team
     * @param request Team update request
     * @return The updated team
     */
    public Team updateTeam(UpdateTeamRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException(request.getTeamId()));
        team.setName(request.getName());
        team.setSeed(request.getSeed());
        team.setColor(request.getColor());
        return teamRepository.save(team);
    }

    /**
     * Add a team member to a team
     * @param request Team Member Add request
     * @return The created TeamMember
     */
    public TeamMember addTeamMember(AddTeamMemberRequest request) {
        Team team = teamRepository.getOne(request.getTeamId());
        User user = userRepository.getOne(request.getUserId());
        return teamMemberRepository.save(new TeamMember(team, user));
    }

    /**
     * Remove a team member from a team
     * @param request Team Member Removal request
     */
    public void removeTeamMember(RemoveTeamMemberRequest request) {
        teamMemberRepository.getTeamMemberByTeamIdAndPlayerId(request.getTeamId(), request.getUserId())
                .ifPresent(tm -> teamMemberRepository.delete(tm));
    }

    /**
     * Delete a team
     * @param teamId The ID of the team to delete
     */
    public void deleteTeam(long teamId) {
        teamRepository.deleteById(teamId);
    }
}
