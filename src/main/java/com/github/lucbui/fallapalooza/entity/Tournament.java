package com.github.lucbui.fallapalooza.entity;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true)
public class Tournament extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @Column(name = "sign_up_start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime signUpStartDate;

    @Column(name = "sign_up_end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime signUpEndDate;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime endDate;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private List<Team> teams;

    /**
     * Add a team to this tournament
     * @param team The team to add
     */
    public void addTeam(Team team) {
        if(teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
    }
}
