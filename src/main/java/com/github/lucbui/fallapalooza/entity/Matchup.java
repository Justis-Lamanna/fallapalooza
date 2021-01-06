package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = {"teamOne", "previousMatchupTeamOne", "teamTwo", "previousMatchupTeamTwo", "round"})
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"team_one_id", "round_id"}),
                @UniqueConstraint(columnNames = {"team_two_id", "round_id"}),
                @UniqueConstraint(columnNames = {"round_id", "matchup_order"})
        }
)
public class Matchup extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("${api.matchup.id}")
    private Long id;

    @Column(name = "matchup_order")
    @ApiModelProperty("${api.matchup.matchupOrder}")
    private int matchupOrder;

    @ManyToOne
    @JoinColumn(name = "team_one_id", referencedColumnName = "id")
    @ApiModelProperty("${api.matchup.teamOne}")
    private Team teamOne;

    @OneToOne
    @JoinColumn(name = "team_one_previous_matchup_id", referencedColumnName = "id")
    @ApiModelProperty("${api.matchup.previousMatchupTeamOne}")
    @JsonIgnore
    private Matchup previousMatchupTeamOne;

    @ManyToOne
    @JoinColumn(name = "team_two_id", referencedColumnName = "id")
    @ApiModelProperty("${api.matchup.teamTwo}")
    private Team teamTwo;

    @OneToOne
    @JoinColumn(name = "team_two_previous_matchup_id", referencedColumnName = "id")
    @ApiModelProperty("${api.matchup.previousMatchupTeamTwo}")
    @JsonIgnore
    private Matchup previousMatchupTeamTwo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    @ApiModelProperty("${api.matchup.round}")
    private Round round;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty("${api.matchup.winner}")
    private Winner winner = Winner.UNDECIDED;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP")
    @ApiModelProperty("${api.matchup.startDate}")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    @ApiModelProperty("${api.matchup.endDate}")
    private OffsetDateTime endDate;

    public enum Winner {
        UNDECIDED,
        TEAM_ONE,
        TEAM_TWO
    }
}
