package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.Round;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.repository.*;
import com.github.lucbui.fallapalooza.service.TeamService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class FallapaloozaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(FallapaloozaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FallapaloozaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			TeamService teamService,
			TournamentRepository tournamentRepository,
			TeamMemberRepository teamMemberRepository,
			TeamRepository teamRepository,
			UserRepository userRepository,
			RoundRepository roundRepository,
			ScoreRepository scoreRepository) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());
			fall.setSignUpStartDate(OffsetDateTime.now().minus(10, ChronoUnit.MINUTES));
			Tournament fallFinal = tournamentRepository.save(fall);

			Round round1 = roundRepository.save(new Round(1, "Round 1", fall));
			Round round2 = roundRepository.save(new Round(2, "Round 2", fall));
			Round round3 = roundRepository.save(new Round(3, "Quarterfinals", fall));
			Round round4 = roundRepository.save(new Round(4, "Semifinals", fall));
			Round round5 = roundRepository.save(new Round(5, "Finals", fall));

			List<User> users = IntStream.range(0, 100)
					.mapToObj(x -> userRepository.save(new User(RandomStringUtils.randomAlphabetic(6, 15))))
					.collect(Collectors.toList());

			Collections.shuffle(users);
			List<User> parallelSafeUsers = Collections.synchronizedList(users);

			List<Team> teams = IntStream.range(0, 40)
					.parallel()
					.mapToObj(x -> {
						CreateTeamRequest ctr = new CreateTeamRequest();
						ctr.setName(RandomStringUtils.randomAlphabetic(10, 30));
						ctr.setTournamentId(fallFinal.getId());
						List<CreateTeamRequest.CreateMemberRequest> cmrs = new ArrayList<>();
						CreateTeamRequest.CreateMemberRequest cmr1 = new CreateTeamRequest.CreateMemberRequest();
						cmr1.setId(parallelSafeUsers.remove(0).getId());
						cmrs.add(cmr1);
						CreateTeamRequest.CreateMemberRequest cmr2 = new CreateTeamRequest.CreateMemberRequest();
						cmr2.setId(parallelSafeUsers.remove(0).getId());
						cmrs.add(cmr2);
						ctr.setMembers(cmrs);
						return teamService.createTeam(ctr);
					})
					.collect(Collectors.toList());

// Round 1
//			scoreRepository.save(new Score(round1, m1, 1, 370));
//			scoreRepository.save(new Score(round1, m1, 2, 1280));
//			scoreRepository.save(new Score(round1, m1, 3, 335));
//			scoreRepository.save(new Score(round1, m2, 1,1280));
//			scoreRepository.save(new Score(round1, m2, 2,510));
//			scoreRepository.save(new Score(round1, m2, 3,1245));
//
//			// Round 2
//			scoreRepository.save(new Score(round2, m1, 1, 510));
//			scoreRepository.save(new Score(round2, m1, 2, 1350));
//			scoreRepository.save(new Score(round2, m1, 3, 545));
//			scoreRepository.save(new Score(round2, m2, 1,475));
//			scoreRepository.save(new Score(round2, m2, 2,475));
//			scoreRepository.save(new Score(round2, m2, 3,1280));
//
//			// QFs
//			scoreRepository.save(new Score(round3, m1, 1, 300));
//			scoreRepository.save(new Score(round3, m1, 2, 405));
//			scoreRepository.save(new Score(round3, m1, 3, 1245));
//			scoreRepository.save(new Score(round3, m2, 1,250));
//			scoreRepository.save(new Score(round3, m2, 2,475));
//			scoreRepository.save(new Score(round3, m2, 3,1245));
//
//			// SFs
//			scoreRepository.save(new Score(round4, m1, 1, 545));
//			scoreRepository.save(new Score(round4, m1, 2, 510));
//			scoreRepository.save(new Score(round4, m1, 3, 545));
//			scoreRepository.save(new Score(round4, m2, 1,1175));
//			scoreRepository.save(new Score(round4, m2, 2,1175));
//			scoreRepository.save(new Score(round4, m2, 3,475));
//
//			// Finals
//			scoreRepository.save(new Score(round5, m1, 1, 510));
//			scoreRepository.save(new Score(round5, m1, 2, 1245));
//			scoreRepository.save(new Score(round5, m1, 3, 545));
//			scoreRepository.save(new Score(round5, m1, 4, 1245));
//			scoreRepository.save(new Score(round5, m1, 5, 615));
//			scoreRepository.save(new Score(round5, m2, 1,545));
//			scoreRepository.save(new Score(round5, m2, 2,475));
//			scoreRepository.save(new Score(round5, m2, 3,250));
//			scoreRepository.save(new Score(round5, m2, 4, 405));
//			scoreRepository.save(new Score(round5, m2, 5, 510));
		};
	}
}
