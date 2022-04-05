package com.projectteam.coop.sample.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
public class JpaSample {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAMPLE_SEQ")
    private Long sampleSeq;

    private String id;

    private String name;

    private String age;

}
