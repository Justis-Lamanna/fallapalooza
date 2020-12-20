package com.github.lucbui.fallapalooza;

import com.github.lucbui.fallapalooza.entity.Tournament;
import com.github.lucbui.fallapalooza.entity.User;
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

@SpringBootApplication
public class FallapaloozaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(FallapaloozaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FallapaloozaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TournamentRepository repository) {
		return (args) -> {
			repository.save(new Tournament("fallapalooza"));
			repository.save(new Tournament("fallapalooza two"));

			repository.findAll().forEach(u -> LOGGER.info(u.toString()));
		};
	}
}
