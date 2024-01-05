package com.projectteam.coop.web.menu.summonerPageForm;

import com.projectteam.coop.tft.domain.MatchDesc;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class SummonerForm {
    private int profileIconId;
    private String name;
    private String puuid;
    private long summonerLevel;
    private String tier;
    private String ranks;
    private int leaguePoints;
    private int wins;
    private int losses;
    private int rankDefense;

    public int getRankDefense(List<MatchDesc> matchDescs){
        int rankDefense = 0;
        int i;

        for(i=0; i<matchDescs.size(); i++){
            if(i < 20){
                if(matchDescs.get(i).getPlacement() < 5){
                    rankDefense++;
                }
            }
        }

        return rankDefense;
    }
}
