package com.github.lucbui.fallapalooza.model.team;

import com.github.lucbui.fallapalooza.entity.Region;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class SimpleCreateTeamRequest {
    @NotNull
    @ApiModelProperty(value = "${api.tournament.id}", required = true)
    private Long tournamentId;

    @NotBlank(message = "Must provide Team Name")
    @ApiModelProperty(value = "${api.team.name}", required = true)
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    @ApiModelProperty("${api.team.color}")
    private String color;

    @ApiModelProperty("${api.team.preferredRegion}")
    private Region preferredRegion;

    @ApiModelProperty("${api.team.seed}")
    private Integer seed;

    @Size(min = 2, message = "Must have at least two members to a team")
    @ApiModelProperty(required = true)
    private List<UserIdentifier> userIdentifiers;

    @Data
    public static class UserIdentifier {
        @NotBlank(message = "Discord ID must be present")
        @ApiModelProperty(value = "${api.user.discordId}", required = true)
        private String discordId;

        @NotBlank(message = "Twitch ID must be present")
        @ApiModelProperty(value = "${api.user.twitchId}", required = true)
        private String twitchId;

        @ApiModelProperty(value = "${api.user.twitterId}")
        private String twitterId;

        @NotBlank(message = "Must provide player name")
        @ApiModelProperty(value = "${api.user.name}", required = true)
        private String name;

        @ApiModelProperty(value = "${api.user.pronouns}")
        private String pronouns;

        @ApiModelProperty(value = "${api.user.blurb}")
        private String blurb;

        @PositiveOrZero(message = "Crown Count must be non-negative")
        @ApiModelProperty(value = "${api.user.crownCount}")
        private Integer crownCount;

        @ApiModelProperty(value = "${api.teamMember.backup}")
        private boolean backup;
    }
}
