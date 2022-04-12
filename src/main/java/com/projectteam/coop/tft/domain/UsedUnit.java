package com.projectteam.coop.tft.domain;

import lombok.Getter;

@Getter
public class UsedUnit implements Comparable<UsedUnit>{
    private String usedAnotherUnit;
    private int count = 0;

    public UsedUnit createUsedUnit(String unit) {
        UsedUnit usedUnit = new UsedUnit();
        usedUnit.usedAnotherUnit = unit;
        usedUnit.count = 1;
        return usedUnit;
    }

    public void unitCounting(UsedUnit usedUnit) {
        usedUnit.count = usedUnit.count + 1;
    }

    @Override
    public int compareTo(UsedUnit usedUnit) {
        if (usedUnit.count > count) {
            return 1;
        } else if (usedUnit.count < count) {
            return -1;
        }
        return 0;
    }
}