package com.projectteam.coop.web.menu.summonerPageForm;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Traits {
    private String name;
    private String numUnits;
    private String style;
    private String total;

    public List<Traits> createTraits(String name, String numUnits, String style, String total){
        List<Traits> userTraits = new ArrayList<>();
        String[] traitsName = name.split("\\|");
        String[] traitsNumUnits = numUnits.split("\\|");
        String[] traitsStyle = style.split("\\|");
        String[] traitsTotal = total.split("\\|");
        int i;

        for(i=0; i<traitsName.length; i++){
            Traits traits = new Traits();
            traits.setName(traitsName[i].substring(5));
            traits.setNumUnits(traitsNumUnits[i]);
            traits.setStyle(traitsStyle[i]);
            traits.setTotal(traitsTotal[i]);
            userTraits.add(traits);
        }
        return userTraits;
    }
}
