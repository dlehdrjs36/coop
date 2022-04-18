package com.projectteam.coop.web.menu;

import com.projectteam.coop.tft.domain.Synergy;
import com.projectteam.coop.tft.service.SynergyService;
import com.projectteam.coop.util.TftUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final SynergyService synergyService;
    private final TftUtil tftUtil = new TftUtil();

    @GetMapping(value = "/augmentsList")
    public String getAugmentsListPage(Model model) {
        return "/templates/tft/augmentsList";
    }

    @GetMapping(value = "/championList")
    public String getChampionListPage(Model model) {
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/championList/{championName}")
    public String searchChampion(@PathVariable String championName, Model model) {
        if(championName != "") {
            model.addAttribute(championName, championName);
        } else {
            model.addAttribute(championName, "");
        }
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/synergyList")
    public String getSynergyListPage(Model model) {
        List<SynergyNameForm> SynergyNameForm = null;
        Synergy initSynergy = null;
        String[] augments;
        String augmentTier = null;

        try {
            SynergyNameForm = synergyService.getSynergyNameList();
            initSynergy = synergyService.findSynergyData("Set6_Enchanter");
            augments = initSynergy.getUsedAugments().split("\\|");
            augmentTier = tftUtil.getAugmentTier(augments);
        }catch(Exception e){
        }finally {
            model.addAttribute("synergyNameForm", SynergyNameForm);
            model.addAttribute("initSynergy", initSynergy);
            model.addAttribute("augmentTier", augmentTier);
        }

        return "/templates/tft/synergyList";
    }

    @GetMapping("/synergyList/{synergyName}")
    @ResponseBody
    public HashMap<String, Object> getSynergyData(@PathVariable String synergyName){
        HashMap<String, Object> SynergyData = new HashMap<>();
        Synergy initSynergy = synergyService.findSynergyData(synergyName);
        String[] augments = initSynergy.getUsedAugments().split("\\|");
        String augmentTier = tftUtil.getAugmentTier(augments);
        SynergyData.put("initSynergy", initSynergy);
        SynergyData.put("augmentTier",augmentTier);
        return SynergyData;
    }

    @GetMapping("/combination")
    public String getCombinationPage(Model model){
        return "/templates/tft/combination";
    }

    @GetMapping("/guide")
    public String getGuidePage(Model model){
        return "/templates/tft/guide";
    }
}