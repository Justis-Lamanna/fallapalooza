package com.github.lucbui.fallapalooza;

import com.github.javafaker.Faker;
import com.github.lucbui.fallapalooza.entity.Team;
import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.model.team.CreateTeamRequest;
import com.github.lucbui.fallapalooza.model.tournament.QuickCreateTournamentRequest;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.service.MatchupService;
import com.github.lucbui.fallapalooza.service.TeamService;
import com.github.lucbui.fallapalooza.service.TournamentService;
import com.github.lucbui.fallapalooza.service.UserService;
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
	private static final Faker FAKER = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(FallapaloozaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			TournamentService tournamentService,
			UserService userService,
			TeamService teamService,
			MatchupService matchupService) {
		return (args) -> {

			QuickCreateTournamentRequest qctr = new QuickCreateTournamentRequest();
			qctr.setName("Fallapalooza");
			qctr.setSignUpStartDate(OffsetDateTime.now().minus(10, ChronoUnit.MINUTES));
			qctr.setStartDate(OffsetDateTime.now().plus(5, ChronoUnit.DAYS));
			Tournament fallFinal = tournamentService.createStandard(qctr);

			List<User> users = IntStream.range(0, 100)
					.mapToObj(x -> {
						CreateUserRequest cur = new CreateUserRequest();
						cur.setName(FAKER.name().username());
						cur.setCrownCount(5);
						cur.setTwitterId("twitter_" + x);
						cur.setTwitchId("twitch_" + x);
						cur.setDiscordId("discord_" + x);
						return userService.create(cur);
					})
					.collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
						Collections.shuffle(l); return Collections.synchronizedList(l);
					}));

			List<Team> teams = IntStream.range(0, 40)
					.parallel()
					.mapToObj(x -> {
						String teamName = "Team " + x;
						CreateTeamRequest ctr = new CreateTeamRequest();
						ctr.setName(teamName);
						ctr.setTournamentId(fallFinal.getId());
						ctr.setSeed(x);
						List<CreateTeamRequest.CreateMemberRequest> cmrs = new ArrayList<>();
						CreateTeamRequest.CreateMemberRequest cmr1 = new CreateTeamRequest.CreateMemberRequest();
						cmr1.setId(users.remove(0).getId());
						cmrs.add(cmr1);
						CreateTeamRequest.CreateMemberRequest cmr2 = new CreateTeamRequest.CreateMemberRequest();
						cmr2.setId(users.remove(0).getId());
						cmrs.add(cmr2);
						ctr.setMembers(cmrs);
						return teamService.create(ctr);
					})
					.collect(Collectors.toList());
//
//			InitializeMatchupRequest imr = IntStream.range(0, 16)
//					.mapToObj(x -> new Seeds.Seed(2 * x, (2 * x) + 1)).collect(Collectors.collectingAndThen(Collectors.toList(), seeds -> {
//						InitializeMatchupRequest i = new InitializeMatchupRequest();
//						i.setTournamentId(fallFinal.getId());
//						i.setSeeds(new Seeds(seeds));
//						return i;
//					}));
//
//			List<Matchup> matchups = matchupService.initializeMatchups(imr);
		};
	}
}
