package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.MatchDesc;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MatchDescForm {
    private String matchId;
    private long gameLength;
    private List<UserDesc> usersData;


    public MatchDescForm createMatchDescForm(List<MatchDesc> matchDesc, List<String> playerNameList){
        MatchDescForm matchDescForm = new MatchDescForm();
        UserDesc userDesc = new UserDesc();
        matchDescForm.matchId = matchDesc.get(0).getMetadateMatchId();
        matchDescForm.gameLength = matchDesc.get(0).getGameLength();
        matchDescForm.usersData = userDesc.createUserDesc(matchDesc, playerNameList);

        return matchDescForm;
    }
}
