package com.projectteam.coop.web.menu;

import com.projectteam.coop.tft.domain.MatchDescDTO.MatchDescDTO;
import com.projectteam.coop.util.TftUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping(value = "/augmentsList")
    public String getAugmentsListPage(Model model) {
        return "/templates/tft/augmentsList";
    }

    @GetMapping(value = "/championList")
    public String getChampionListPage(Model model) {
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/test")
    public String test(Model model) {

        TftUtil tftUtil = new TftUtil();
        MatchDescDTO matchDescDTO = tftUtil.getTftMatchDesc("KR_5861969135","RGAPI-22121760-e55b-4d46-94d2-705d9af2de4a");

        return "/templates/tft/championList";
    }

}