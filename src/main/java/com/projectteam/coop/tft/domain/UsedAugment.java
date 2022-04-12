package com.projectteam.coop.tft.domain;

import lombok.Getter;

@Getter
public class UsedAugment implements Comparable<UsedAugment> {
    private String usedAnotherAugment;
    private int count = 0;

    public UsedAugment createUsedAugment(String augment) {
        UsedAugment usedAugment = new UsedAugment();
        usedAugment.usedAnotherAugment = augment;
        usedAugment.count = 1;
        return usedAugment;
    }

    public void augmentCounting(UsedAugment usedAugment) {
        usedAugment.count = usedAugment.count + 1;
    }

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