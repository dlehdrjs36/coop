package com.projectteam.coop.tft.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsedAugment implements Comparable<UsedAugment> {
    private String usedAnotherAugment;
    private int count = 0;

    @Override
    public int compareTo(UsedAugment usedAugment) {
        if (usedAugment.count > count) {
            return 1;
        } else if (usedAugment.count < count) {
            return -1;
        }
        return 0;
    }
}