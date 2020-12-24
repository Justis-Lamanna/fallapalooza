package com.github.lucbui.fallapalooza.entity;

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
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User player;

    private boolean backup;
}
