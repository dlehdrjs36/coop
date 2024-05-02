package com.projectteam.coop.sample.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class JpaSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAMPLE_SEQ")
    private Long sampleSeq;

    private String id;

    private String name;

    private String age;

}
