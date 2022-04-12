package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class InfoParticipantsCompanion {
    @JsonProperty(value = "content_ID")
    private String contentId;
    @JsonProperty(value = "skin_ID")
    private int skinId;
    private String species;
}