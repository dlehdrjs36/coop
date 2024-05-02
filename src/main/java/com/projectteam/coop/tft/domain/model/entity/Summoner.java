package com.projectteam.coop.tft.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

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