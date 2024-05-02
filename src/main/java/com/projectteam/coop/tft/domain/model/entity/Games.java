package com.projectteam.coop.tft.domain.model.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@IdClass(GamesId.class)
@Table(name ="games", catalog = "coop")
public class Games {

    @Id
    @Column(name = "PARTICIPANTS")
    private String participant;
    @Id
    @Column(name = "MATCH_ID")
    private String matchId;

    // $ 혹은 | 를 입력해서 데이터화 시킬 예정 List 대부분 그럴예정
    @Column(name = "AUGMENTS")
    private String augments;

    //전체 게임 시간
    @Column(name = "GAME_DATETIME")
    private long gameDatetime;
    @Column(name = "GAME_LENGTH")
    private long gameLength;
    @Column(name = "GAME_VERSION")
    private String gameVersion;

    @Column(name = "COMPANION_CONTENT_ID")
    private String companionContentId;
    @Column(name = "COMPANION_SKIN_ID")
    private int companionSkinId;
    @Column(name = "COMPANION_SPECIES")
    private String companionSpecies;

    @Column(name = "PLACEMENT")
    private int placement;
    @Column(name = "PUUID")
    private String puuid;
    @Column(name = "TIME_ELIMINATED")
    private long timeEliminated;

    @Column(name = "TRAITS_NAME", length = 1000)
    private String traitsName;
    @Column(name = "TRAITS_NUM_UNITS")
    private String traitsNumUnits;
    // 이 특성의 현재 스타일입니다. (0 = 스타일 없음, 1 = 브론즈, 2 = 실버, 3 = 골드, 4 = 유채색)
    @Column(name = "TRAITS_STYLE")
    private String traitsStyle;
    // 토탈 티어중에 Current가 몇 달성했는지
    @Column(name = "TRAITS_TIER_CURRENT")
    private String traitsTierCurrent;
    @Column(name = "TRAITS_TIER_TOTAL")
    private String traitsTierTotal;

    @Column(name = "UNITS_CHARACTER_ID")
    private String unitsCharacterId;
    @Column(name = "UNITS_ITEM_NAMES", length = 1000)
    private String unitsItemNames;
    @Column(name = "UNITS_NAME")
    private String unitsName;
    // 희귀도 0 ~ 4 까지 1골드 ~ 5골드
    @Column(name = "UNITS_RARITY")
    private String unitsRarity;
    // 1 ~ 3 티어
    @Column(name = "UNITS_TIER")
    private String unitsTier;
    @Column(name = "QUEUE_ID")
    private int queueId;
    @Column(name = "TFT_GAME_TYPE")
    private String tftGameType;

    public Games createMatchDesc(String participant, String matchId, long gameTime, long gameLength, String gameVersion, String augments,
                                 String companionContentID, int companionSkinID, String companionSpecies, int placement, long timeEliminated, String puuid,
                                 String traitsName, String traitsNumUnits, String traitsStyle, String traitsTierCurrent, String traitsTierTotal,
                                 String unitsCharacterId, String unitsItemNames, String unitsName, String unitsRarity, String unitsTier,
                                 int queueId, String tftGameType) {
        Games games = new Games();
        games.participant = participant;
        games.matchId = matchId;
        games.gameDatetime = gameTime;
        games.gameLength = gameLength;
        games.gameVersion = gameVersion;
        games.augments = augments;
        games.companionContentId = companionContentID;
        games.companionSkinId = companionSkinID;
        games.companionSpecies = companionSpecies;
        games.placement = placement;
        games.timeEliminated = timeEliminated;
        games.puuid = puuid;
        games.traitsName = traitsName;
        games.traitsNumUnits = traitsNumUnits;
        games.traitsStyle = traitsStyle;
        games.traitsTierCurrent = traitsTierCurrent;
        games.traitsTierTotal = traitsTierTotal;
        games.unitsCharacterId = unitsCharacterId;
        games.unitsItemNames = unitsItemNames;
        games.unitsName = unitsName;
        games.unitsRarity = unitsRarity;
        games.unitsTier = unitsTier;
        games.queueId = queueId;
        games.tftGameType = tftGameType;

        return games;
    }
}