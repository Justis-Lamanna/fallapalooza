package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Enumerated(EnumType.ORDINAL)
    private Region preferredRegion;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    @JsonIgnore
    private Tournament tournament;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> members;
}
