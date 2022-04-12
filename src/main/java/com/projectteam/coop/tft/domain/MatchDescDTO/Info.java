package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Info {
    @JsonProperty(value = "game_datetime")
    private long gameDatetime;
    @JsonProperty(value = "game_length")
    private long gameLength;
    @JsonProperty(value = "game_version")
    private String gameVersion;

    private List<InfoParticipants> participants;

    @JsonProperty(value = "queue_id")
    private int queueId;
    @JsonProperty(value = "tft_game_type")
    private String tftGameType;
    @JsonProperty(value = "tft_set_number")
    private int tftSetNumber;
}
