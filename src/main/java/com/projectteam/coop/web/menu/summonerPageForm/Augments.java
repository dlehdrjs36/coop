package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.util.TftUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Augments {
    private String augment;
    private String tier;

    public List<Augments> createAugments(String augment){
        TftUtil tftUtil = new TftUtil();
        List<Augments> usedAugments = new ArrayList<>();
        String[] augmentList = augment.split("\\|");
        String[] augmentsTmp = tftUtil.getAugmentTier(augmentList).split("\\|");
        int i;
        for(i=0; i<augmentList.length; i++){
            Augments augments = new Augments();
            augments.augment = augmentList[i].substring(13);
            augments.tier = augmentsTmp[i];
            usedAugments.add(augments);
        }

        return usedAugments;
    }
}
