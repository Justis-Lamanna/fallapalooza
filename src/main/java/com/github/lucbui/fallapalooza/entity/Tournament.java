package com.github.lucbui.fallapalooza.entity;

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
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Column(name = "sign_up_start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime signUpStartDate;

    @Column(name = "sign_up_end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime signUpEndDate;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime endDate;
}
