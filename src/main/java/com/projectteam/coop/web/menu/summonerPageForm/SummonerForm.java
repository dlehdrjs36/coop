package com.projectteam.coop.web.menu.summonerPageForm;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SummonerForm {
    private int profileIconId;
    private String name;
    private String puuid;
    private long summonerLevel;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
}
