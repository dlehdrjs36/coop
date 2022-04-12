package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class InfoParticipants {
    private List<String> augments;
    private InfoParticipantsCompanion companion;

    @JsonProperty(value = "gold_left")
    private int goldLeft;
    @JsonProperty(value = "last_round")
    private int lastRound;
    private int level;
    private int placement;
    @JsonProperty(value = "players_eliminated")
    private int playersEliminated;
    private String puuid;
    @JsonProperty(value = "time_eliminated")
    private long timeEliminated;
    @JsonProperty(value = "total_damage_to_players")
    private int totalDamageToPlayers;

    private List<InfoParticipantsTraits> traits;
    private List<InfoParticipantsUnits> units;

}
