package com.github.lucbui.fallapalooza.model.team;

import com.github.lucbui.fallapalooza.entity.Region;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class CreateTeamAndUserRequest {
    @NotNull
    private Long tournamentId;

    @NotBlank(message = "Must provide Team Name")
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    private String color;

    private Region preferredRegion;

    private Integer seed;

    @Size(min = 2, message = "Must have at least two members to a team")
    private List<UserIdentifier> userIdentifiers;

    @Data
    public static class UserIdentifier {
        @NotBlank(message = "Discord ID must be present")
        private String discordId;

        @NotBlank(message = "Twitch ID must be present")
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
