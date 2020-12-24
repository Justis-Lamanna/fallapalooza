package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
