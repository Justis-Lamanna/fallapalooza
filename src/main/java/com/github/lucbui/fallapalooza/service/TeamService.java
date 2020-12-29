package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.InvalidSignUpException;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.model.team.AddTeamMemberRequest;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.UpdateTeamRequest;
import com.github.lucbui.fallapalooza.repository.TeamMemberRepository;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

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
    @Transactional
    public Team createTeam(CreateTeamRequest request) {
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(request.getTournamentId()));
        OffsetDateTime now = OffsetDateTime.now();

        if(tournament.getSignUpStartDate() == null || now.isBefore(tournament.getSignUpStartDate())) {
            throw new InvalidSignUpException("Sign-up not started");
        } else if(tournament.getSignUpEndDate() != null && now.isAfter(tournament.getSignUpEndDate())) {
            throw new InvalidSignUpException("Sign-up date passed");
        }

        Team team = new Team(request.getName(), tournament);
        team.setColor(request.getColor());
        team.setSeed(request.getSeed());
        team = teamRepository.save(team);

        int numberOfActiveMembers = 0;
        for(CreateTeamRequest.CreateMemberRequest cmRequest : request.getMembers()) {
            TeamMember member = new TeamMember(team, userRepository.getOne(cmRequest.getId()));
            member.setBackup(numberOfActiveMembers > 2 || cmRequest.isBackup());
            member = teamMemberRepository.save(member);
            if(!member.isBackup()) {
                numberOfActiveMembers++;
            }
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
        TeamMember tm = new TeamMember(team, user);
        tm.setBackup(request.isBackup());
        return teamMemberRepository.save(tm);
    }

    /**
     * Remove a team member from a team
     * @param memberId TeamMember ID to remove
     */
    public void removeTeamMember(long memberId) {
        teamMemberRepository.deleteById(memberId);
    }

    /**
     * Delete a team
     * @param teamId The ID of the team to delete
     */
    public void deleteTeam(long teamId) {
        teamRepository.deleteById(teamId);
    }
}
