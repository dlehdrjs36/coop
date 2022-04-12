package com.projectteam.coop.tft.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Summoner {

    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    @Id
    private String id;
    private String puuid;
    private long summonerLevel;
}