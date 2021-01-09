package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.*;
import com.github.lucbui.fallapalooza.exception.InvalidSignUpException;
import com.github.lucbui.fallapalooza.exception.TeamNotFoundException;
import com.github.lucbui.fallapalooza.exception.TournamentNotFoundException;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.SimpleCreateTeamRequest;
import com.github.lucbui.fallapalooza.model.team.UpdateTeamRequest;
import com.github.lucbui.fallapalooza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
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

    @Autowired
    private ParticipantRepository participantRepository;

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
            TeamMember member = new TeamMember(team, participantRepository.getOne(cmRequest.getId()));
            member.setBackup(numberOfActiveMembers > 2 || cmRequest.isBackup());
            member = teamMemberRepository.save(member);
            if(!member.isBackup()) {
                numberOfActiveMembers++;
            }
        }

        return team;
    }

    @Transactional
    public Team create(SimpleCreateTeamRequest request) {
        Tournament tournament = getAndValidateTournament(request.getTournamentId());

        Team team = new Team(request.getName(), tournament);
        team.setColor(request.getColor());
        team.setPreferredRegion(request.getPreferredRegion());
        team.setSeed(request.getSeed());
        team = teamRepository.save(team);

        for(SimpleCreateTeamRequest.UserIdentifier dId : request.getUserIdentifiers()) {
            //Sign up the user, if necessary
            User user = getUserByIdType(dId)
                    .orElseGet(() -> new User(dId.getName(), dId.getTwitchId(), dId.getDiscordId()));

            user.setName(dId.getName());
            user.setPronouns(dId.getPronouns());
            user.setBlurb(dId.getBlurb());
            user.setCrownCount(dId.getCrownCount());
            user.setTwitterId(dId.getTwitterId());

            User finalUser = userRepository.save(user);

            //Enroll as participant in the tournament
            Participant participant = participantRepository.getParticipantByTournamentIdAndUserId(request.getTournamentId(), finalUser.getId())
                    .orElseGet(() -> participantRepository.save(new Participant(tournament, finalUser)));

            //Join the team
            TeamMember member = new TeamMember(team, participant);
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

    private Optional<User> getUserByIdType(SimpleCreateTeamRequest.UserIdentifier id) {
        return userRepository.getUserByDiscordId(id.getTwitchId());
    }

    /**
     * Update a team
     * @param request Team update request
     * @return The updated team
     */
    public Team update(UpdateTeamRequest request) {
        Team team = getById(request.getTeamId());
        if (request.getName() != null) { team.setName(request.getName()); }
        if (request.getSeed() != null) { team.setSeed(request.getSeed()); }
        if (request.getColor() != null) { team.setColor(request.getColor()); }
        if (request.getPreferredRegion() != null) { team.setPreferredRegion(request.getPreferredRegion()); }
        return teamRepository.save(team);
    }

    /**
     * Get a team by their ID
     * @param id The team ID
     * @return The team
     */
    public Team getById(long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    /**
     * Retrieve teams by a pageable
     * @param pageable The pageable to use
     * @return The requested page of teams
     */
    public Page<Team> getByPageable(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    /**
     * Retrieve all teams in a tournament
     * @param tournamentId The tournament ID
     * @return The teams of that tournament
     */
    public List<Team> getByTournamentId(long tournamentId) {
        return teamRepository.getTeamByTournamentId(tournamentId);
    }

    /**
     * Get the active teams (first 32 signed-up teams)
     * @param tournamentId The tournament ID
     * @return The teams
     */
    public List<Team> getActiveTeamsByTournamentId(long tournamentId) {
        return teamRepository.getFirst32ByTournamentIdOrderByCreatedDateAsc(tournamentId);
    }
}
