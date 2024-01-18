package com.projectteam.coop.tft.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "summoner", catalog = "coop")
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