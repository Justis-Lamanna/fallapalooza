package com.github.lucbui.fallapalooza.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString(callSuper = true)
public class User extends Auditable<String> {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    private String pronouns;

    private String blurb;

    private Integer crownCount;

    @Column(unique = true)
    private String discordId;

    @Column(unique = true)
    private String twitchId;

    @Column(unique = true)
    private String twitterId;
}
