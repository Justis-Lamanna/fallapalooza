package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.Participant;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.model.participant.ParticipateRequest;
import com.github.lucbui.fallapalooza.repository.ParticipantRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    public Participant join(ParticipateRequest request) {
        User user = userRepository.getOne(request.getUserId());
        Tournament tournament = tournamentRepository.getOne(request.getTournamentId());

        return participantRepository.save(new Participant(tournament, user));
    }
}
