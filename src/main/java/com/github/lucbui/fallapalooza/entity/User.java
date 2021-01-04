package com.github.lucbui.fallapalooza.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("${api.user.id}")
    private Long id;

    @NonNull
    @Column(nullable = false)
    @ApiModelProperty("${api.user.name}")
    private String name;

    @ApiModelProperty("${api.user.pronouns}")
    private String pronouns;

    @ApiModelProperty("${api.user.blurb}")
    private String blurb;

    @ApiModelProperty("${api.user.crownCount}")
    private Integer crownCount;

    @NonNull
    @Column(unique = true)
    @ApiModelProperty("${api.user.discordId}")
    private String discordId;

    @NonNull
    @Column(unique = true)
    @ApiModelProperty("${api.user.twitchId}")
    private String twitchId;

    @Column(unique = true)
    @ApiModelProperty("${api.user.twitterId}")
    private String twitterId;
}
