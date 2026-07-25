package com.projectteam.coop.web.menu.enums;

public enum TftJsonTag {

    SPELL_PASSIVE("spellPassive"),
    TFT_TRACKER_LABEL("TFTTrackerLabel"),
    MAGIC_DAMAGE("magicDamage"),
    PHYSICAL_DAMAGE("physicalDamage"),
    TFT_BONUS("TFTBonus"),
    SPELL_ACTIVE("spellActive"),
    SHOW_IF_NOT("ShowIfNot"),
    SHOW_IF("ShowIf")
    ;
    private String tagName;

    TftJsonTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}