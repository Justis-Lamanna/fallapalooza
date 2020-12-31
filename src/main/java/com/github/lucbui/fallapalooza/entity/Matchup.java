package com.github.lucbui.fallapalooza.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = {"teamOne", "teamTwo", "round"})
public class Matchup extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_one_id", referencedColumnName = "id")
    private Team teamOne;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_two_id", referencedColumnName = "id")
    private Team teamTwo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;
}
