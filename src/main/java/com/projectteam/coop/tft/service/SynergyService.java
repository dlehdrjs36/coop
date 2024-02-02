package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.Games;
import com.projectteam.coop.tft.domain.Synergy;
import com.projectteam.coop.tft.domain.UsedAugment;
import com.projectteam.coop.tft.domain.UsedUnit;
import com.projectteam.coop.tft.repository.MatchDescRepository;
import com.projectteam.coop.tft.repository.SynergyRepository;
import com.projectteam.coop.web.menu.SynergyNameForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class SynergyService {

    private final MatchDescRepository matchDescRepository;
    private final SynergyRepository synergyRepository;

    public void initSynergyDesc() {
        List<Games> gamesData = matchDescRepository.searchTftPlacementData(1);
        List<Synergy> synergyList = synergyRepository.findAll();
        double winPoint;

        for (Synergy synergy : synergyList) {
            winPoint = 0;
            List<UsedUnit> usedUnitList = new ArrayList<>();
            List<UsedAugment> usedAugmentsList = new ArrayList<>();
            for (Games gamesDatum : gamesData) {
                if (gamesDatum.getTraitsName().contains(synergy.getTraitsName())) {
                    String[] TraitsName = gamesDatum.getTraitsName().split("\\|");
                    String[] TraitsStyle = gamesDatum.getTraitsStyle().split("\\|");
                    for (int k = 0; k < TraitsName.length; k++) {
                        if (synergy.getTraitsName().equals(TraitsName[k]) && !"0".equals(TraitsStyle[k])) {
                            winPoint++;
                            usedUnitList = usedSynergyUnitCount(usedUnitList, gamesDatum.getUnitsCharacterId());
                            usedAugmentsList = usedAugmentCount(usedAugmentsList, gamesDatum.getAugments());
                        }
                    }
                }
            }

            Collections.sort(usedUnitList);
            Collections.sort(usedAugmentsList);
            winPoint = winPoint / gamesData.size() * 100;
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

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public Synergy findSynergyData(String synergyName){
        return synergyRepository.findSynergy(synergyName);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<Synergy> findAllSynergyData(){
        return synergyRepository.findAll();
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<String> findAllSynergyName(){
        return synergyRepository.findAllSynergyName();
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
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

    public List<SynergyNameForm> getSynergyNameList(){
        List<SynergyNameForm> synergyName = new ArrayList<>();
        List<String> synergyListName = findAllSynergyName();
        List<String> synergyListNameKr = findAllSynergyNameKr();
        int i;

        for(i=0; i<synergyListName.size(); i++){
            SynergyNameForm SynergyNameForm = new SynergyNameForm();
            SynergyNameForm.setTraitsName(synergyListName.get(i));
            SynergyNameForm.setTraitsNameKr(synergyListNameKr.get(i));
            synergyName.add(SynergyNameForm);
        }

        return synergyName;
    }
}