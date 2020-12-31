package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateTeamAndUserRequest {
    private long tournamentId;

    @NotBlank(message = "Must provide Team Name")
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    private String color;

    @Size(min = 2, message = "Must have at least two members to a team")
    private List<UserIdentifier> userIdentifiers;

    @Data
    public static class UserIdentifier {
        private IdType idType;

        private String discordId;

        private String twitchId;

        private String twitterId;

        @NotBlank(message = "Must provide player name")
        private String name;

        private String pronouns;

        private String blurb;

        @PositiveOrZero(message = "Crown Count must be non-negative")
        private Integer crownCount;

        private boolean backup;
    }
}
