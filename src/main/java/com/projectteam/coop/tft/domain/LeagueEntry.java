package com.projectteam.coop.tft.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class LeagueEntry {
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    @Id
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
}