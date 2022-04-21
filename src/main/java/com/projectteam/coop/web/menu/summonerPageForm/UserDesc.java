package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.MatchDesc;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserDesc {
    private String name;
    private List<Augments> augments;
    private int placement;
    private String puuid;
    private List<Traits> traits;
    private List<Units> units;


    public List<UserDesc> createUserDesc(List<MatchDesc> matchDescList, List<String> playerNameList){
        List<UserDesc> userDescList = new ArrayList<>();
        int i;
        for(i=0; i<matchDescList.size(); i++){
            UserDesc userDesc = new UserDesc();
            Traits traits = new Traits();
            Units units = new Units();
            Augments augments = new Augments();
            userDesc.name = playerNameList.get(i);
            userDesc.augments = augments.createAugments(matchDescList.get(i).getAugments());
            userDesc.placement = matchDescList.get(i).getPlacement();
            userDesc.puuid = matchDescList.get(i).getPuuid();
            userDesc.traits = traits.createTraits(matchDescList.get(i).getTraitsName(),
                                                  matchDescList.get(i).getTraitsNumUnits(),
                                                  matchDescList.get(i).getTraitsStyle(),
                                                  matchDescList.get(i).getTraitsTierTotal());
            userDesc.units = units.createUnits(
                    matchDescList.get(i).getUnitsCharacterId(),
                    matchDescList.get(i).getUnitsItemNames(),
                    matchDescList.get(i).getUnitsRarity(),
                    matchDescList.get(i).getUnitsTier()
            );
            userDescList.add(userDesc);
        }
        return userDescList;
    }
}
