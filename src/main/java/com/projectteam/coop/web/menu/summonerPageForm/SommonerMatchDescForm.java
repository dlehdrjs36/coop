package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.util.TftUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SommonerMatchDescForm {
    private String gameDatetime;
    private MatchDesc matchDesc;
    private List<Units> units;

    public List<SommonerMatchDescForm> createSommonerMatchDescForm(List<MatchDesc> matchDesc, int page){
        List<SommonerMatchDescForm> sommonerMatchDescFormList = new ArrayList<>();
        Units units = new Units();
        TftUtil tftUtil = new TftUtil();
        int i;
        for(i=(page-1)*10; i<matchDesc.size(); i++) {
            if(i < page * 10) {
                SommonerMatchDescForm sommonerMatchDescForm = new SommonerMatchDescForm();
                sommonerMatchDescForm.gameDatetime = tftUtil.getGameDatetime(matchDesc.get(i).getGameDatetime());
                sommonerMatchDescForm.matchDesc = matchDesc.get(i);
                sommonerMatchDescFormList.add(sommonerMatchDescForm);
                sommonerMatchDescForm.units = units.createUnits(
                        matchDesc.get(i).getUnitsCharacterId(),
                        matchDesc.get(i).getUnitsItemNames(),
                        matchDesc.get(i).getUnitsRarity(),
                        matchDesc.get(i).getUnitsTier()
                );
            }
        }
        return sommonerMatchDescFormList;
    }
}
