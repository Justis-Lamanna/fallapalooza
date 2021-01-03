package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.InvalidSignUpException;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.model.team.CreateTeamAndUserRequest;
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
import java.util.Optional;

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
    public Team create(CreateTeamRequest request) {
        Tournament tournament = getAndValidateTournament(request.getTournamentId());

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

    @Transactional
    public Team create(CreateTeamAndUserRequest request) {
        Tournament tournament = getAndValidateTournament(request.getTournamentId());

        Team team = new Team(request.getName(), tournament);
        team.setColor(request.getColor());
        team = teamRepository.save(team);

        for(CreateTeamAndUserRequest.UserIdentifier dId : request.getUserIdentifiers()) {
            User user = getUserByIdType(dId).orElseGet(() -> new User(dId.getName(), dId.getTwitchId(), dId.getDiscordId()));

            user.setName(dId.getName());
            user.setPronouns(dId.getPronouns());
            user.setBlurb(dId.getBlurb());
            user.setCrownCount(dId.getCrownCount());
            user.setTwitterId(dId.getTwitterId());

            user = userRepository.save(user);

            TeamMember member = new TeamMember(team, user);
            member.setBackup(dId.isBackup());
            teamMemberRepository.save(member);
        }

        return team;
    }

    private Tournament getAndValidateTournament(long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        OffsetDateTime now = OffsetDateTime.now();

        if(tournament.getSignUpStartDate() == null || now.isBefore(tournament.getSignUpStartDate())) {
            throw new InvalidSignUpException("Sign-up not started");
        } else if(tournament.getSignUpEndDate() != null && now.isAfter(tournament.getSignUpEndDate())) {
            throw new InvalidSignUpException("Sign-up date passed");
        }

        return tournament;
    }

    private Optional<User> getUserByIdType(CreateTeamAndUserRequest.UserIdentifier id) {
        switch (id.getIdType()) {
            case TWITCH: return userRepository.getUserByTwitchId(id.getTwitchId());
            case DISCORD: return userRepository.getUserByDiscordId(id.getTwitchId());
            case TWITTER: return userRepository.getUserByTwitterId(id.getTwitterId());
            default: return Optional.empty();
        }
    }

    /**
     * Update a team
     * @param request Team update request
     * @return The updated team
     */
    public Team update(UpdateTeamRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException(request.getTeamId()));
        team.setName(request.getName());
        team.setSeed(request.getSeed());
        team.setColor(request.getColor());
        return teamRepository.save(team);
    }
}
