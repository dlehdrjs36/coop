package com.projectteam.coop.tft.domain.MatchDescDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class InfoParticipantsTraits {
    private String name;
    @JsonProperty(value = "num_units")
    private int numUnits;
    private int style;
    @JsonProperty(value = "tier_current")
    private int tierCurrent;
    @JsonProperty(value = "tier_total")
    private int tierTotal;
}
