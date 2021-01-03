package com.github.lucbui.fallapalooza.entity;

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
    private Long id;

    @Column(name = "matchup_order")
    private int matchupOrder;

    @ManyToOne
    @JoinColumn(name = "team_one_id", referencedColumnName = "id")
    private Team teamOne;

    @OneToOne
    @JoinColumn(name = "team_one_previous_matchup_id", referencedColumnName = "id")
    private Matchup previousMatchupTeamOne;

    @ManyToOne
    @JoinColumn(name = "team_two_id", referencedColumnName = "id")
    private Team teamTwo;

    @OneToOne
    @JoinColumn(name = "team_two_previous_matchup_id", referencedColumnName = "id")
    private Matchup previousMatchupTeamTwo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private Winner winner = Winner.UNDECIDED;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    private OffsetDateTime endDate;

    public enum Winner {
        UNDECIDED,
        TEAM_ONE,
        TEAM_TWO
    }
}
