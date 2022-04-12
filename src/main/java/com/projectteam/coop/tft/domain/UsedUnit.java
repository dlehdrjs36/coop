package com.projectteam.coop.tft.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsedUnit implements Comparable<UsedUnit>{
    private String usedAnotherUnit;
    private int count = 0;

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