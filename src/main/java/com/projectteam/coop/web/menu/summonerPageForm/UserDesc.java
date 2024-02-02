package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.Games;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserDesc {
    private String name;
    private List<Augments> augments;
    private int placement;
    private long timeEliminated;
    private String puuid;
    private List<Traits> traits;
    private List<Units> units;

    public List<UserDesc> createUserDesc(List<Games> gamesList, List<String> playerNameList){
        List<UserDesc> userDescList = new ArrayList<>();
        int i;
        for(i=0; i< gamesList.size(); i++){
            UserDesc userDesc = new UserDesc();
            Traits traits = new Traits();
            Units units = new Units();
            Augments augments = new Augments();
            userDesc.name = playerNameList.get(i);
            userDesc.augments = augments.createAugments(gamesList.get(i).getAugments());
            userDesc.placement = gamesList.get(i).getPlacement();
            userDesc.timeEliminated = gamesList.get(i).getTimeEliminated();
            userDesc.puuid = gamesList.get(i).getPuuid();
            userDesc.traits = traits.createTraits(gamesList.get(i).getTraitsName(),
                                                  gamesList.get(i).getTraitsNumUnits(),
                                                  gamesList.get(i).getTraitsStyle(),
                                                  gamesList.get(i).getTraitsTierTotal());
            userDesc.units = units.createUnits(
                    gamesList.get(i).getUnitsCharacterId(),
                    gamesList.get(i).getUnitsItemNames(),
                    gamesList.get(i).getUnitsRarity(),
                    gamesList.get(i).getUnitsTier()
            );
            userDescList.add(userDesc);
        }
        return userDescList;
    }
}
