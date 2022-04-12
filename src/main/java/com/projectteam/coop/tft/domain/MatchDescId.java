package com.projectteam.coop.tft.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MatchDescId implements Serializable {
    private static final long serialVersionUID = 5735022999922182249L;

    @EqualsAndHashCode.Include
    public String metadataParticipants;
    @EqualsAndHashCode.Include
    public String metadateMatchId;
}