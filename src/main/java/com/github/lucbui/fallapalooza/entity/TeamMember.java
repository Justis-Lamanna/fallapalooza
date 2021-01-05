package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = {"team", "player"})
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "user_id"})
)
public class TeamMember extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("${api.teamMember.id}")
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonIgnore
    private Team team;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User player;

    @ApiModelProperty("${api.teamMember.backup}")
    private boolean backup;
}
