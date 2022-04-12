package com.projectteam.coop.tft.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Synergy {
    @Id
    private String traitsName;
    private String traitsNameKr;
    private String traitsDesc;
    private String traitsTier;
    @Column(length=1000)
    private String traitsTierDesc;
    private String traitsNumUnits;
    private String traitsNumUnitsKr;

    private String winRate;
    private String usedAnotherUnit;
    private String usedAugments;
}