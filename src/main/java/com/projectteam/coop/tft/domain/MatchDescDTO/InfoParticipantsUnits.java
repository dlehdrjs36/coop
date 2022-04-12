package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class InfoParticipantsUnits {
    @JsonProperty(value = "character_id")
    private String characterId;
    private List<String> itemNames;
    private List<Integer> items;
    private String name;
    private int rarity;
    private int tier;
}
