package com.projectteam.coop.tft.web;

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
}