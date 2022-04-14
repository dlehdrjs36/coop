package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.tft.domain.Synergy;
import com.projectteam.coop.tft.domain.UsedAugment;
import com.projectteam.coop.tft.domain.UsedUnit;
import com.projectteam.coop.tft.repository.MatchDescRepository;
import com.projectteam.coop.tft.repository.SynergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class SynergyService {

    private final MatchDescRepository matchDescRepository;
    private final SynergyRepository synergyRepository;

    public void initSynergyDesc() {
        List<MatchDesc> matchDescData = matchDescRepository.searchTftPlacementData(1);
        List<Synergy> synergyList = synergyRepository.findAll();
        double winPoint;

        for (Synergy synergy : synergyList) {
            winPoint = 0;
            List<UsedUnit> usedUnitList = new ArrayList<>();
            List<UsedAugment> usedAugmentsList = new ArrayList<>();
            for (MatchDesc matchDescDatum : matchDescData) {
                if (matchDescDatum.getTraitsName().contains(synergy.getTraitsName())) {
                    String[] TraitsName = matchDescDatum.getTraitsName().split("\\|");
                    String[] TraitsStyle = matchDescDatum.getTraitsStyle().split("\\|");
                    for (int k = 0; k < TraitsName.length; k++) {
                        if (synergy.getTraitsName().equals(TraitsName[k]) && !"0".equals(TraitsStyle[k])) {
                            winPoint++;
                            usedUnitList = usedSynergyUnitCount(usedUnitList, matchDescDatum.getUnitsCharacterId());
                            usedAugmentsList = usedAugmentCount(usedAugmentsList, matchDescDatum.getAugments());
                        }
                    }
                }
            }

            Collections.sort(usedUnitList);
            Collections.sort(usedAugmentsList);
            winPoint = winPoint / matchDescData.size() * 100;
            StringBuilder usedUnits = new StringBuilder();
            StringBuilder usedAugments = new StringBuilder();
            if(usedUnitList.size() != 0) {
                for (int i = 0; i < 5; i++) {
                    usedUnits.append(usedUnitList.get(i).getUsedAnotherUnit()).append("|");
                }
                for (int i = 0; i < 3; i++) {
                    usedAugments.append(usedAugmentsList.get(i).getUsedAnotherAugment()).append("|");
                }
                usedUnits = new StringBuilder(usedUnits.substring(0, usedUnits.length() - 1));
                usedAugments = new StringBuilder(usedAugments.substring(0, usedAugments.length() - 1));
            }

            synergyRepository.updateWinRateUnits(String.format("%.2f",winPoint), usedUnits.toString(), usedAugments.toString(), synergy.getTraitsName());
        }
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Synergy findSynergyData(String synergyName){
        return synergyRepository.findSynergy(synergyName);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Synergy> findAllSynergyData(){
        return synergyRepository.findAll();
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<String> findAllSynergyName(){
        return synergyRepository.findAllSynergyName();
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<String> findAllSynergyNameKr(){
        return synergyRepository.findAllSynergyNameKr();
    }

    public List<UsedUnit> usedSynergyUnitCount(List<UsedUnit> usedUnitList, String usedUnit){
        String[] usedUnitArray = usedUnit.split("\\|");
        boolean umuFlg;

        for (String s : usedUnitArray) {
            umuFlg = false;
            for (UsedUnit unit : usedUnitList) {
                if (unit.getUsedAnotherUnit().equals(s)) {
                    unit.unitCounting(unit);
                    umuFlg = true;
                }
            }
            if (umuFlg) {
                continue;
            }

            UsedUnit usedUnits = new UsedUnit();
            usedUnitList.add(usedUnits.createUsedUnit(s));
        }
        return usedUnitList;
    }

    public List<UsedAugment> usedAugmentCount(List<UsedAugment> usedAugmentsList, String usedAugment){
        String[] usedAugmentsArray = usedAugment.split("\\|");
        boolean umuFlg;

        for (String s : usedAugmentsArray) {
            umuFlg = false;
            for (UsedAugment unit : usedAugmentsList) {
                if (unit.getUsedAnotherAugment().equals(s)) {
                    unit.augmentCounting(unit);
                    umuFlg = true;
                }
            }
            if (umuFlg) {
                continue;
            }

            UsedAugment usedAugments = new UsedAugment();
            usedAugmentsList.add(usedAugments.createUsedAugment(s));
        }
        return usedAugmentsList;
    }
}