package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.model.entity.Games;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MatchDescForm {
    private String matchId;
    private long gameLength;
    private List<UserDesc> usersData;


    public MatchDescForm createMatchDescForm(List<Games> games, List<String> playerNameList){
        MatchDescForm matchDescForm = new MatchDescForm();
        UserDesc userDesc = new UserDesc();
        matchDescForm.matchId = games.get(0).getMatchId();
        matchDescForm.gameLength = games.get(0).getGameLength();
        matchDescForm.usersData = userDesc.createUserDesc(games, playerNameList);

        return matchDescForm;
    }
}
