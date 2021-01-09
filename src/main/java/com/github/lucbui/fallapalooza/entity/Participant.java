package com.github.lucbui.fallapalooza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"tournament_id", "user_id"})
)
public class Participant extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    @JsonIgnore
    private Tournament tournament;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
