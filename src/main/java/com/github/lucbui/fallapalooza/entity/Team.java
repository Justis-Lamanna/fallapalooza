package com.github.lucbui.fallapalooza.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true, exclude = "tournament")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tournament_id", "seed"}),
                @UniqueConstraint(columnNames = {"tournament_id", "name"})
        }
)
public class Team extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    private String color;

    private Integer seed;

    private String preferredRegion;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;
}
