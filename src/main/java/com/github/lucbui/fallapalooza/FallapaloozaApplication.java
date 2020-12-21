package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.repository.TeamMemberRepository;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import com.github.lucbui.fallapalooza.repository.UserRepository;
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
	public CommandLineRunner demo(TournamentRepository tournamentRepository, TeamMemberRepository teamMemberRepository, TeamRepository teamRepository, UserRepository userRepository) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());

			Team t1 = teamRepository.save(new Team("Lavender Lemurs"));
			fall.addTeam(t1);
			tournamentRepository.save(fall);

			User p1 = userRepository.save(new User("Pirauxide"));
			TeamMember member = new TeamMember(t1, p1);
			teamMemberRepository.save(member);
		};
	}
}
