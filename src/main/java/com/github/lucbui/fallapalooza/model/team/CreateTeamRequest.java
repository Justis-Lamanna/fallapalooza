package com.github.lucbui.fallapalooza.model.team;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateTeamRequest {
    private long tournamentId;

    @NotBlank(message = "Must provide Team Name")
    private String name;

    @Pattern(regexp = "#[0-9]{6}", message = "Color must be in #xxxxxx format")
    private String color;

    @Size(min = 2, message = "Must have at least two members to a team")
    private List<CreateMemberRequest> members;

    @Data
    public static class CreateMemberRequest {
        private long id;
        private boolean backup;
    }
}
