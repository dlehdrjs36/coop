package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.tft.domain.MatchDescDTO.MatchDescDTO;
import com.projectteam.coop.tft.repository.MatchDescRepository;
import com.projectteam.coop.util.TftUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class MatchDescService {

    private final MatchDescRepository matchDescRepository;
    private final TftUtil tftUtil = new TftUtil();
    private final String tftVersion = "Version 12.6";

    public void addMatchDesc(String puuid, String apikey) {
        List<MatchDesc> matchDesc;
        List<String> matchId = tftUtil.getTftMatchId(puuid, 1,apikey);
        for(int i=0; i<matchId.size(); i++) {
            try {
                MatchDescDTO matchDescDTO = tftUtil.getTftMatchDesc(matchId.get(i), apikey);
                if(matchDescDTO.getInfo().getGameVersion().indexOf(tftVersion) != -1) {
                    if(matchDescRepository.searchTftMatchIdData(matchDescDTO.getMetadata().getMatchId()) == true) {
                        matchDesc = makeMatchDesc(matchDescDTO);
                        for (int j = 0; j < matchDesc.size(); j++) {
                            matchDescRepository.addTftDescData(matchDesc.get(j));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("데이터 수집에 문제가 되는 버전입니다.");
            }
        }
    }

    public List<MatchDesc> makeMatchDesc(MatchDescDTO matchData){
        List<MatchDesc> matchDescList = new ArrayList<>();
        int i=0, j=0, k=0;
        String tmp="", traitsName="", traitsUnits="", traitsStyle="", tierCurrent="", tierTotal="";
        String character_id="", itemNames="", itemNames2="", name="", rarity="", tier="";

        for(i=0; i<matchData.getMetadata().getParticipants().size(); i++){
            MatchDesc matchDesc = new MatchDesc();
            matchDesc.setMetadataParticipants(matchData.getMetadata().getParticipants().get(i));
            matchDesc.setMetadateMatchId(matchData.getMetadata().getMatchId());

            matchDesc.setGameLength(matchData.getInfo().getGameLength());
            matchDesc.setGameVersion(matchData.getInfo().getGameVersion());

            tmp="";
            for(j=0; j<matchData.getInfo().getParticipants().get(i).getAugments().size(); j++) {
                tmp += matchData.getInfo().getParticipants().get(i).getAugments().get(j) + "|";
            }
            tmp = tmp.substring(0, tmp.length() - 1);
            matchDesc.setAugments(tmp);

            matchDesc.setCompanionContentId(matchData.getInfo().getParticipants().get(i).getCompanion().getContentId());
            matchDesc.setCompanionSkinId(matchData.getInfo().getParticipants().get(i).getCompanion().getSkinId());
            matchDesc.setCompanionSpecies(matchData.getInfo().getParticipants().get(i).getCompanion().getSpecies());

            matchDesc.setPlacement(matchData.getInfo().getParticipants().get(i).getPlacement());
            matchDesc.setPuuid(matchData.getInfo().getParticipants().get(i).getPuuid());

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

            matchDesc.setTraitsName(traitsName);
            matchDesc.setTraitsNumUnits(traitsUnits);
            matchDesc.setTraitsStyle(traitsStyle);
            matchDesc.setTraitsTierCurrent(tierCurrent);
            matchDesc.setTraitsTierTotal(tierTotal);

            character_id="";
            itemNames="";
            name="";
            rarity="";
            tier="";
            for(j=0; j<matchData.getInfo().getParticipants().get(i).getUnits().size(); j++) {
                character_id += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getCharacterId() + "|";
                itemNames2 = "";
                for(k=0; k<matchData.getInfo().getParticipants().get(i).getUnits().get(j).getItemNames().size(); k++){
                    itemNames2 += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getItemNames().get(k) + "$";
                }
                if(itemNames2.isEmpty() != true) {
                    itemNames2 = itemNames2.substring(0, itemNames2.length() - 1);
                }
                itemNames += itemNames2 + "|";
                name += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getName() + "|";
                rarity += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getRarity() + "|";
                tier += matchData.getInfo().getParticipants().get(i).getUnits().get(j).getTier() + "|";
            }

            character_id = character_id.substring(0,character_id.length()-1);
            itemNames = itemNames.substring(0,itemNames.length()-1);
            name = name.substring(0,name.length()-1);
            rarity = rarity.substring(0,rarity.length()-1);
            tier = tier.substring(0,tier.length()-1);

            matchDesc.setUnitsCharacterId(character_id);
            matchDesc.setUnitsItemNames(itemNames);
            matchDesc.setUnitsName(name);
            matchDesc.setUnitsRarity(rarity);
            matchDesc.setUnitstier(tier);

            matchDesc.setTftGameType(matchData.getInfo().getTftGameType());
            matchDesc.setTftSetNumber(matchData.getInfo().getTftSetNumber());

            matchDescList.add(matchDesc);
        }
        return matchDescList;
    }
}