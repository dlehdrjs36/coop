package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.MatchDescDTO.MatchDescDTO;
import com.projectteam.coop.tft.domain.model.entity.Games;
import com.projectteam.coop.tft.repository.MatchDescRepository;
import com.projectteam.coop.util.TftUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchDescService {

    private final MatchDescRepository matchDescRepository;
    private final TftUtil tftUtil = new TftUtil();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addMatchDesc(String puuid, String apikey) {
        List<Games> games;
        List<String> matchId = tftUtil.getTftMatchId(puuid, 20,apikey);
        String gameDateString = "";
        Date gameDate;

        try {
            Date comparisonDate = dateFormat.parse("2022-02-16");
            for (String s : matchId) {
                MatchDescDTO matchDescDTO = tftUtil.getTftMatchDesc(s, apikey);
                gameDateString = dateFormat.format(matchDescDTO.getInfo().getGameDatetime());
                gameDate = dateFormat.parse(gameDateString);
                if (comparisonDate.before(gameDate)) {
                    if (matchDescRepository.isTftMatchIdData(matchDescDTO.getMetadata().getMatchId())) {
                        games = makeMatchDesc(matchDescDTO);
                        for (Games desc : games) {
                            matchDescRepository.addTftDescData(desc);
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("데이터 수집에 문제가 되는 버전입니다.");
        }
    }

    public List<Games> getMatchDescDataPuuid(String puuid){
        return matchDescRepository.searchTftPuuidData(puuid);
    }

    public List<Games> getMatchDescDataMatchId(String matchid){
        return matchDescRepository.searchTftMatchIdData(matchid);
    }

    public List<Games> makeMatchDesc(MatchDescDTO matchData){
        List<Games> gamesList = new ArrayList<>();
        int i, j, k;
        String augments, traitsName, traitsUnits, traitsStyle, tierCurrent, tierTotal;
        String characterId, itemNames, itemNames2, name, rarity, tier;

        for(i=0; i<matchData.getMetadata().getParticipants().size(); i++){
            Games games = new Games();
            augments="";
            for(j=0; j<matchData.getInfo().getParticipants().get(i).getAugments().size(); j++) {
                augments += matchData.getInfo().getParticipants().get(i).getAugments().get(j) + "|";
            }
            augments = augments.substring(0, augments.length() - 1);
            traitsName="";
            traitsUnits="";
            traitsStyle="";
            tierCurrent="";
            tierTotal="";

            for(j=0; j<matchData.getInfo().getParticipants().get(i).getTraits().size(); j++) {
                traitsName += matchData.getInfo().getParticipants().get(i).getTraits().get(j).getName() + "|";
                traitsUnits += matchData.getInfo().getParticipants().get(i).getTraits().get(j).getNumUnits() + "|";
                traitsStyle += matchData.getInfo().getParticipants().get(i).getTraits().get(j).getStyle() + "|";
                tierCurrent += matchData.getInfo().getParticipants().get(i).getTraits().get(j).getTierCurrent() + "|";
                tierTotal += matchData.getInfo().getParticipants().get(i).getTraits().get(j).getTierTotal() + "|";
            }

            traitsName = traitsName.substring(0,traitsName.length()-1);
            traitsUnits = traitsUnits.substring(0,traitsUnits.length()-1);
            traitsStyle = traitsStyle.substring(0,traitsStyle.length()-1);
            tierCurrent = tierCurrent.substring(0,tierCurrent.length()-1);
            tierTotal = tierTotal.substring(0,tierTotal.length()-1);

            characterId="";
            itemNames="";
            name="";
            rarity="";
            tier="";
            for(j=0; j<matchData.getInfo().getParticipants().get(i).getUnits().size(); j++) {
                characterId += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getCharacterId() + "|";
                itemNames2 = "";
                for(k=0; k<matchData.getInfo().getParticipants().get(i).getUnits().get(j).getItemNames().size(); k++){
                    itemNames2 += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getItemNames().get(k) + "$";
                }
                if(!itemNames2.isEmpty()) {
                    itemNames2 = itemNames2.substring(0, itemNames2.length() - 1);
                }
                itemNames += itemNames2 + "|";
                name += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getName() + "|";
                rarity += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getRarity() + "|";
                tier += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getTier() + "|";
            }

            characterId = characterId.substring(0,characterId.length()-1);
            itemNames = itemNames.substring(0,itemNames.length()-1);
            name = name.substring(0,name.length()-1);
            rarity = rarity.substring(0,rarity.length()-1);
            tier = tier.substring(0,tier.length()-1);

            gamesList.add(
                    games.createMatchDesc(
                            matchData.getMetadata().getParticipants().get(i),
                            matchData.getMetadata().getMatchId(),
                            matchData.getInfo().getGameDatetime(),
                            matchData.getInfo().getGameLength(),
                            matchData.getInfo().getGameVersion(),
                            augments,
                            matchData.getInfo().getParticipants().get(i).getCompanion().getContentId(),
                            matchData.getInfo().getParticipants().get(i).getCompanion().getSkinId(),
                            matchData.getInfo().getParticipants().get(i).getCompanion().getSpecies(),
                            matchData.getInfo().getParticipants().get(i).getPlacement(),
                            matchData.getInfo().getParticipants().get(i).getTimeEliminated(),
                            matchData.getInfo().getParticipants().get(i).getPuuid(),
                            traitsName,
                            traitsUnits,
                            traitsStyle,
                            tierCurrent,
                            tierTotal,
                            characterId,
                            itemNames,
                            name,
                            rarity,
                            tier,
                            matchData.getInfo().getQueueId(),
                            matchData.getInfo().getTftGameType()
                    )
            );
        }
        return gamesList;
    }
}