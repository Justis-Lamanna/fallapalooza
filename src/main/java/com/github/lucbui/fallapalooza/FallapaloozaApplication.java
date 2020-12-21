package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.*;
import com.github.lucbui.fallapalooza.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.OffsetDateTime;

@SpringBootApplication
public class FallapaloozaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(FallapaloozaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FallapaloozaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			TournamentRepository tournamentRepository,
			TeamMemberRepository teamMemberRepository,
			TeamRepository teamRepository,
			UserRepository userRepository,
			RoundRepository roundRepository) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());
			fall = tournamentRepository.save(fall);

			Round round1 = new Round(1, "Round 1");
			round1.setTournament(fall);
			round1 = roundRepository.save(round1);
			Round round2 = new Round(2, "Round 2");
			round2.setTournament(fall);
			round2 = roundRepository.save(round2);
			Round round3 = new Round(3, "Round 3");
			round3.setTournament(fall);
			round3 = roundRepository.save(round3);

			Team t1 = new Team("Lavender Lemurs");
			t1.setTournament(fall);
			t1 = teamRepository.save(t1);

			User p1 = new User("Pirauxide");
			p1 = userRepository.save(p1);

			User p2 = new User("BHappen");
			p2 = userRepository.save(p2);

			teamMemberRepository.save(new TeamMember(t1, p1));
			teamMemberRepository.save(new TeamMember(t1, p2));
		};
	}
}
