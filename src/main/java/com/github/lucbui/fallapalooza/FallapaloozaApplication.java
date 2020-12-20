package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.repository.TeamRepository;
import com.github.lucbui.fallapalooza.repository.TournamentRepository;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class FallapaloozaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(FallapaloozaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FallapaloozaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository ur, TeamRepository tr, TournamentRepository r) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());
			Team t1 = new Team("Lavender Lemurs");
			Team t2 = new Team("Team Lame");
			User p1 = new User("Pirauxide");
			User p2 = new User("BHappen");
			User p3 = new User("Lucbui");
			t1.setUsers(Arrays.asList(p1, p2));
			t2.setUsers(Arrays.asList(p3));
			fall.setTeams(Arrays.asList(t1, t2));

			r.save(fall);

			r.findAll().forEach(t -> LOGGER.info(t.toString()));
		};
	}
}
