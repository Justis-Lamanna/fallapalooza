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
			RoundRepository roundRepository,
			ScoreRepository scoreRepository) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());
			fall = tournamentRepository.save(fall);

			Round round1 = roundRepository.save(new Round(1, "Round 1", fall));
			Round round2 = roundRepository.save(new Round(2, "Round 2", fall));
			Round round3 = roundRepository.save(new Round(3, "Quarterfinals", fall));
			Round round4 = roundRepository.save(new Round(4, "Semifinals", fall));
			Round round5 = roundRepository.save(new Round(5, "Finals", fall));

			Team t1 = teamRepository.save(new Team("DELETE FALL MOUNTAIN", fall));

			User p1 = userRepository.save(new User("Triumph"));
			User p2 = userRepository.save(new User("ChewyLewy"));

			TeamMember m1 = teamMemberRepository.save(new TeamMember(t1, p1));
			TeamMember m2 = teamMemberRepository.save(new TeamMember(t1, p2));

			// Round 1
			scoreRepository.save(new Score(round1, m1, 1, 370));
			scoreRepository.save(new Score(round1, m1, 2, 1280));
			scoreRepository.save(new Score(round1, m1, 3, 335));
			scoreRepository.save(new Score(round1, m2, 1,1280));
			scoreRepository.save(new Score(round1, m2, 2,510));
			scoreRepository.save(new Score(round1, m2, 3,1245));

			// Round 2
			scoreRepository.save(new Score(round2, m1, 1, 510));
			scoreRepository.save(new Score(round2, m1, 2, 1350));
			scoreRepository.save(new Score(round2, m1, 3, 545));
			scoreRepository.save(new Score(round2, m2, 1,475));
			scoreRepository.save(new Score(round2, m2, 2,475));
			scoreRepository.save(new Score(round2, m2, 3,1280));

			// QFs
			scoreRepository.save(new Score(round3, m1, 1, 300));
			scoreRepository.save(new Score(round3, m1, 2, 405));
			scoreRepository.save(new Score(round3, m1, 3, 1245));
			scoreRepository.save(new Score(round3, m2, 1,250));
			scoreRepository.save(new Score(round3, m2, 2,475));
			scoreRepository.save(new Score(round3, m2, 3,1245));

			// SFs
			scoreRepository.save(new Score(round4, m1, 1, 545));
			scoreRepository.save(new Score(round4, m1, 2, 510));
			scoreRepository.save(new Score(round4, m1, 3, 545));
			scoreRepository.save(new Score(round4, m2, 1,1175));
			scoreRepository.save(new Score(round4, m2, 2,1175));
			scoreRepository.save(new Score(round4, m2, 3,475));

			// Finals
			scoreRepository.save(new Score(round5, m1, 1, 510));
			scoreRepository.save(new Score(round5, m1, 2, 1245));
			scoreRepository.save(new Score(round5, m1, 3, 545));
			scoreRepository.save(new Score(round5, m1, 4, 1245));
			scoreRepository.save(new Score(round5, m1, 5, 615));
			scoreRepository.save(new Score(round5, m2, 1,545));
			scoreRepository.save(new Score(round5, m2, 2,475));
			scoreRepository.save(new Score(round5, m2, 3,250));
			scoreRepository.save(new Score(round5, m2, 4, 405));
			scoreRepository.save(new Score(round5, m2, 5, 510));
		};
	}
}
