package com.projectteam.coop.web.menu;

import com.jcraft.jsch.Channel;
import com.projectteam.coop.tft.domain.Synergy;
import com.projectteam.coop.tft.service.SynergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final SynergyService synergyService;

    @GetMapping(value = "/augmentsList")
    public String getAugmentsListPage(Model model) {
        return "/templates/tft/augmentsList";
    }

    @GetMapping(value = "/championList")
    public String getChampionListPage(Model model) {
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/synergyList")
    public String getSynergyListPage(Model model) {
        List<String> synergyListName = synergyService.findAllSynergyName();
        List<String> synergyListNameKr = synergyService.findAllSynergyNameKr();
        List<SynergyNameForm> synergyNameForm = new ArrayList<>();
        String augmentPath = "D:/project/origin/coop/src/main/resources/static/img/tft/augment/tier";
        String augmentTier;
        String[] augments;
        int i, j;

        for(i=0; i<synergyListName.size(); i++){
            SynergyNameForm SynergyName = new SynergyNameForm();
            SynergyName.setTraitsName(synergyListName.get(i));
            SynergyName.setTraitsNameKr(synergyListNameKr.get(i));
            synergyNameForm.add(SynergyName);
        }
        Synergy initSynergy = synergyService.findSynergyData("Set6_Enchanter");

        augments = initSynergy.getUsedAugments().split("\\|");
        augmentTier = "";
        for(i=0; i<3; i++){
            for(j=1; j<4; j++){
                File file1 = new File(augmentPath + j + "/" + augments[i].substring(13, augments[i].length())+".png");
                if(file1.exists()){
                    augmentTier += String.valueOf(j) + '|';
                }
            }
        }
        if(!augmentTier.equals("")) {
            augmentTier = augmentTier.substring(0, augmentTier.length() - 1);
        }

        model.addAttribute("synergyNameForm", synergyNameForm);
        model.addAttribute("initSynergy", initSynergy);
        model.addAttribute("augmentTier", augmentTier);
        return "/templates/tft/synergyList";
    }

}