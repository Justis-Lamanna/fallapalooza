package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = {"matchup", "teamMember"})
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"matchup_id", "member_id", "episode"})
)
public class Score extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "matchup_id", referencedColumnName = "id")
    @JsonIgnore
    private Matchup matchup;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private TeamMember teamMember;

    @NonNull
    private Integer episode;

    @NonNull
    private Integer score;
}
