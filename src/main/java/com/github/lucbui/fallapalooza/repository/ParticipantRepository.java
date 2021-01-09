package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> getParticipantByTournamentId(long tournamentId);

    Optional<Participant> getParticipantByTournamentIdAndUserId(long tournamentId, long userId);
}
