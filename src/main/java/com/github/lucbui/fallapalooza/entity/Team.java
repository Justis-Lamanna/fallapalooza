package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("${api.team.id}")
    private Long id;

    @NonNull
    @Column(nullable = false)
    @ApiModelProperty("${api.team.name}")
    private String name;

    @ApiModelProperty("${api.team.color}")
    private String color;

    @ApiModelProperty("${api.team.seed}")
    private Integer seed;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty("${api.team.preferredRegion}")
    private Region preferredRegion;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    @JsonIgnore
    private Tournament tournament;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> members;
}
