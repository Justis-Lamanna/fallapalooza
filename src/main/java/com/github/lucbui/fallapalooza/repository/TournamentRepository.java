package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
