package com.projectteam.coop.web.tft;

import com.projectteam.coop.tft.service.MatchDescService;
import com.projectteam.coop.util.TftUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SummonerController {
    private final TftUtil tftUtil = new TftUtil();
    private final String apkKey = "RGAPI-fbabcaa2-430f-4104-abb1-7863a9c18878";
    private final MatchDescService matchDescService;

    @GetMapping(value="/recordUpdate")
    public String doGet(){
        return "redirect:/";
    }

    @PostMapping(value="/recordUpdate")
    public String updateUserRecord(Model model, HttpServletRequest request){
        String puuid = request.getParameter("puuid");
        String name = request.getParameter("name");
        matchDescService.addMatchDesc(puuid,apkKey);

        return "redirect:/summoner/"+name;
    }
}
