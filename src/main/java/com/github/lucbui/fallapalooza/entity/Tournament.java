package com.github.lucbui.fallapalooza.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    @ApiModelProperty("${api.tournament.id}")
    private Long id;

    @NonNull
    @Column(nullable = false)
    @ApiModelProperty("${api.tournament.name}")
    private String name;

    @Column(name = "sign_up_start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @ApiModelProperty("${api.tournament.signUpStartDate}")
    private OffsetDateTime signUpStartDate;

    @Column(name = "sign_up_end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @ApiModelProperty("${api.tournament.signUpEndDate}")
    private OffsetDateTime signUpEndDate;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @ApiModelProperty("${api.tournament.startDate}")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @ApiModelProperty("${api.tournament.endDate}")
    private OffsetDateTime endDate;
}
