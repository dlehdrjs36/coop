package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Metadata {
    private List<String> participants;
    @JsonProperty(value = "match_id")
    private String matchId;
    @JsonProperty(value = "data_version")
    private String dataVersion;
}
