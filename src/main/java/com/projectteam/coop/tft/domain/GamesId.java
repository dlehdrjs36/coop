package com.projectteam.coop.tft.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GamesId implements Serializable {
    private static final long serialVersionUID = 5735022999922182249L;

    @EqualsAndHashCode.Include
    public String participant;
    @EqualsAndHashCode.Include
    public String matchId;
}