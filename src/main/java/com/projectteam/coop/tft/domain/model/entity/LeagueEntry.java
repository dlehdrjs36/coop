package com.projectteam.coop.tft.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "leagueentry", catalog = "coop")
public class LeagueEntry {
    @Id
    private String summonerName;
    private String leagueId;
    private String queueType;
    private String tier;
    private String ranks;
    private String summonerId;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
}