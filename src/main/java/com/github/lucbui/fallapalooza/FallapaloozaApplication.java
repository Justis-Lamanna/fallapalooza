package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.TeamMember;
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

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public CommandLineRunner demo(TournamentRepository r) {
		return (args) -> {
			Tournament fall = new Tournament("Fallapalooza");
			fall.setStartDate(OffsetDateTime.now());
			Team t1 = new Team("Lavender Lemurs");
			User common = new User("BHappen");
			t1.addTeamMember(new TeamMember(new User("Pirauxide")));
			t1.addTeamMember(new TeamMember(common));
			fall.addTeam(t1);
			Team t2 = new Team("Team Lame");
			t2.addTeamMember(new TeamMember(new User("Lucbui")));
			t2.addTeamMember(new TeamMember(common));
			fall.addTeam(t2);

			r.save(fall);

			r.findAll().forEach(t -> LOGGER.info(t.toString()));
		};
	}
}
