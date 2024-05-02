package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.model.entity.Games;
import com.projectteam.coop.util.TftUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SommonerMatchDescForm {
    private String gameDatetime;
    private Games games;
    private List<Units> units;

    public List<SommonerMatchDescForm> createSommonerMatchDescForm(List<Games> games, int page){
        List<SommonerMatchDescForm> sommonerMatchDescFormList = new ArrayList<>();
        Units units = new Units();
        TftUtil tftUtil = new TftUtil();
        int i;
        for(i=(page-1)*10; i< games.size(); i++) {
            if(i < page * 10) {
                SommonerMatchDescForm sommonerMatchDescForm = new SommonerMatchDescForm();
                sommonerMatchDescForm.gameDatetime = tftUtil.getGameDatetime(games.get(i).getGameDatetime());
                sommonerMatchDescForm.games = games.get(i);
                sommonerMatchDescFormList.add(sommonerMatchDescForm);
                sommonerMatchDescForm.units = units.createUnits(
                        games.get(i).getUnitsCharacterId(),
                        games.get(i).getUnitsItemNames(),
                        games.get(i).getUnitsRarity(),
                        games.get(i).getUnitsTier()
                );
            }
        }
        return sommonerMatchDescFormList;
    }
}
