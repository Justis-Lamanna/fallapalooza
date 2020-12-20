package com.github.lucbui.fallapalooza.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true)
public class Team extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;
    private String color;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private List<TeamMember> members;

    /**
     * Add a member to this tournament
     * @param member The member to add
     */
    public void addTeamMember(TeamMember member) {
        if(members == null) {
            members = new ArrayList<>();
        }
        members.add(member);
    }
}
