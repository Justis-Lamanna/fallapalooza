package com.github.lucbui.fallapalooza.model.team;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateTeamRequest {
    @NotNull
    @ApiModelProperty(value = "${api.tournament.id}", required = true)
    private Long tournamentId;

    @NotBlank(message = "Must provide Team Name")
    @ApiModelProperty(value = "${api.team.name}", required = true)
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    @ApiModelProperty("${api.team.color}")
    private String color;

    @Size(min = 2, message = "Must have at least two members to a team")
    @ApiModelProperty(required = true)
    private List<CreateMemberRequest> members;

    @ApiModelProperty("${api.team.seed}")
    private Integer seed;

    @Data
    public static class CreateMemberRequest {
        @NotNull
        @ApiModelProperty(value = "${api.participant.id}", required = true)
        private Long id;

        @ApiModelProperty("${api.teamMember.backup}")
        private boolean backup;
    }
}
