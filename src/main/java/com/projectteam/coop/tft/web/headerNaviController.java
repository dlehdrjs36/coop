package com.projectteam.coop.tft.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class headerNaviController {

    @GetMapping(value = "/augmentsList")
    public String getAugmentationListPage(Model model) {
        return "/templates/tft/augmentsList";
    }
}