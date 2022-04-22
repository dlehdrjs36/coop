package com.projectteam.coop.web.tft;

import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.tft.service.MatchDescService;
import com.projectteam.coop.util.TftUtil;
import com.projectteam.coop.web.menu.summonerPageForm.MatchDescForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SummonerController {
    private final TftUtil tftUtil = new TftUtil();
    private final MatchDescService matchDescService;
    @Value("${coop.riot.apiKey}")
    private String apikey;

    @GetMapping(value="/record")
    public String doGet(){
        return "redirect:/";
    }

    @PostMapping(value="/record")
    public String updateUserRecord(HttpServletRequest request){
        String puuid = request.getParameter("puuid");
        String name = request.getParameter("name");
        matchDescService.addMatchDesc(puuid,apikey);

        return "redirect:/summoner/"+name;
    }

    @GetMapping(value="/summoner/matchData/{matchId}")
    @ResponseBody
    public HashMap<String, Object> getMatchDescData(@PathVariable String matchId){
        HashMap<String, Object> matchData = new HashMap<>();
        MatchDescForm matchDescForm = new MatchDescForm();
        List<String> matchPlayerNameList = new ArrayList<>();
        int i;

        List<MatchDesc> matchDescs = matchDescService.getMatchDescDataMatchId(matchId);
        for(i=0; i<matchDescs.size(); i++){
            matchPlayerNameList.add(tftUtil.getTftPuiidToNameList(matchDescs.get(i).getPuuid(),apikey));
        }
        matchDescForm = matchDescForm.createMatchDescForm(matchDescs, matchPlayerNameList);
        matchData.put("matchData", matchDescForm);
        return matchData;
    }
}
