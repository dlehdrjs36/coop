package com.projectteam.coop.tft.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
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

    public void changeSynergy(Synergy synergy, String winRate, String usedAnotherUnit, String usedAugments) {
        synergy.winRate = winRate;
        synergy.usedAnotherUnit = usedAnotherUnit;
        synergy.usedAugments = usedAugments;
    }

    public Synergy createSynergy(String traitsName, String traitsNameKr, String traitsDesc, String traitsTier,
                              String traitsTierDesc, String traitsNumUnits, String traitsNumUnitsKr){
        Synergy synergy = new Synergy();
        synergy.traitsName = traitsName;
        synergy.traitsNameKr = traitsNameKr;
        synergy.traitsDesc = traitsDesc;
        synergy.traitsTier = traitsTier;
        synergy.traitsTierDesc = traitsTierDesc;
        synergy.traitsNumUnits = traitsNumUnits;
        synergy.traitsNumUnitsKr = traitsNumUnitsKr;
        synergy.winRate = "";
        synergy.usedAnotherUnit = "";
        synergy.usedAugments = "";

        return synergy;
    }
}