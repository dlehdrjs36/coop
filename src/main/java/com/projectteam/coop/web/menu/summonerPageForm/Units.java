package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.util.TftUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class Units {
    private String characterId;
    private List<String> itemNames;
    private String rarity;
    private String tier;

    public List<Units> createUnits(String characterId, String unitItemNames, String rarity, String tier){
        List<Units> userUnits = new ArrayList<>();
        TftUtil tftUtil = new TftUtil();
        String[] unitsCharacterId = characterId.split("\\|");
        String[] itemNames = unitItemNames.split("\\|",unitsCharacterId.length);
        String[] unitsRarity = rarity.split("\\|");
        String[] unitsTier = tier.split("\\|");
        int i, j;

        for(i=0; i<unitsCharacterId.length; i++){
            Units units = new Units();
            units.setCharacterId(unitsCharacterId[i]);
            units.itemNames = Arrays.asList(itemNames[i].split("\\$"));
            for(j=0; j<units.itemNames.size(); j++) {
                if(!units.itemNames.get(j).equals("")){
                    units.itemNames.set(j,tftUtil.getTftItemMappingName(units.itemNames.get(j)));
                }
            }
            units.setRarity(unitsRarity[i]);
            units.setTier(unitsTier[i]);
            userUnits.add(units);
        }
        return userUnits;
    }
}
