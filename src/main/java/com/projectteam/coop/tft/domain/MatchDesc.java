package com.projectteam.coop.tft.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@IdClass(MatchDescId.class)
public class MatchDesc {

    @Id
    private String metadataParticipants;
    @Id
    private String metadateMatchId;
    //전체 게임 시간
    private long gameDatetime;
    private long gameLength;
    private String gameVersion;
    // $ 혹은 | 를 입력해서 데이터화 시킬 예정 List 대부분 그럴예정
    private String augments;

    private String companionContentId;
    private int companionSkinId;
    private String companionSpecies;

    private int placement;
    private String puuid;
    private long timeEliminated;

    @Column(length=1000)
    private String traitsName;
    private String traitsNumUnits;
    // 이 특성의 현재 스타일입니다. (0 = 스타일 없음, 1 = 브론즈, 2 = 실버, 3 = 골드, 4 = 유채색)
    private String traitsStyle;
    // 토탈 티어중에 Current가 몇 달성했는지
    private String traitsTierCurrent;
    private String traitsTierTotal;

    private String unitsCharacterId;
    @Column(length=1000)
    private String unitsItemNames;
    /*private String unitsItems;*/
    private String unitsName;
    // 희귀도 0 ~ 4 까지 1골드 ~ 5골드
    private String unitsRarity;
    // 1 ~ 3 티어
    private String unitsTier;

    private int queueId;
    private String tftGameType;

    public MatchDesc createMatchDesc(String metadataParticipants, String metaDateMatchId, long gameDatetime,long gameLength, String gameVersion, String augments,
                                     String companionContentID, int companionSkinID, String companionSpecies, int placement, long timeEliminated,String puuid,
                                     String traitsName, String traitsNumUnits, String traitsStyle, String traitsTierCurrent, String traitsTierTotal,
                                     String unitsCharacterId, String unitsItemNames, String unitsName, String unitsRarity, String unitsTier,
                                     int queueId,String tftGameType) {
        MatchDesc matchDesc = new MatchDesc();
        matchDesc.metadataParticipants = metadataParticipants;
        matchDesc.metadateMatchId = metaDateMatchId;
        matchDesc.gameDatetime = gameDatetime;
        matchDesc.gameLength = gameLength;
        matchDesc.gameVersion = gameVersion;
        matchDesc.augments = augments;
        matchDesc.companionContentId = companionContentID;
        matchDesc.companionSkinId = companionSkinID;
        matchDesc.companionSpecies = companionSpecies;
        matchDesc.placement = placement;
        matchDesc.timeEliminated = timeEliminated;
        matchDesc.puuid = puuid;
        matchDesc.traitsName = traitsName;
        matchDesc.traitsNumUnits = traitsNumUnits;
        matchDesc.traitsStyle = traitsStyle;
        matchDesc.traitsTierCurrent = traitsTierCurrent;
        matchDesc.traitsTierTotal = traitsTierTotal;
        matchDesc.unitsCharacterId = unitsCharacterId;
        matchDesc.unitsItemNames = unitsItemNames;
        matchDesc.unitsName = unitsName;
        matchDesc.unitsRarity = unitsRarity;
        matchDesc.unitsTier = unitsTier;
        matchDesc.queueId = queueId;
        matchDesc.tftGameType = tftGameType;

        return matchDesc;
    }
}