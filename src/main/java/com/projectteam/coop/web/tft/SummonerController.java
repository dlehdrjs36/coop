package com.projectteam.coop.web.tft;

import com.projectteam.coop.tft.domain.model.entity.Games;
import com.projectteam.coop.tft.service.MatchDescService;
import com.projectteam.coop.util.TftUtil;
import com.projectteam.coop.web.menu.summonerPageForm.MatchDescForm;
import com.projectteam.coop.web.menu.summonerPageForm.SommonerMatchDescForm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
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

    @GetMapping(value="/tft/record")
    public String doGet(){
        return "redirect:/";
    }

    @PostMapping(value="/tft/record")
    public String updateUserRecord(HttpServletRequest request){
        String puuid = request.getParameter("puuid");
        String name = request.getParameter("name");
        matchDescService.addMatchDesc(puuid,apikey);
        try {
            name = URLEncoder.encode(name, "UTF-8");
        }catch(Exception e){
        }
        return "redirect:/tft/summoner/"+name;
    }

    @GetMapping(value="/tft/record/{page}")
    @ResponseBody
    public HashMap<String, Object> getMatchDataPage(@PathVariable int page, HttpServletRequest request){
        String puuid = request.getParameter("puuid");
        List<Games> sommonerGames = matchDescService.getMatchDescDataPuuid(puuid);
        List<SommonerMatchDescForm> sommonerMatchDescFormList = new ArrayList<>();
        SommonerMatchDescForm sommonerMatchDescForm = new SommonerMatchDescForm();
        HashMap<String, Object> matchData = new HashMap<>();

        if(sommonerGames.size() == 0){
        }else{
            sommonerMatchDescFormList = sommonerMatchDescForm.createSommonerMatchDescForm(sommonerGames, page);
        }
        matchData.put("sommonerMatchData",sommonerMatchDescFormList);

        return matchData;
    }

    @GetMapping(value="/tft/summoner/matchData/{matchId}")
    @ResponseBody
    public HashMap<String, Object> getMatchDescData(@PathVariable String matchId){
        HashMap<String, Object> matchDescData = new HashMap<>();
        MatchDescForm matchDescForm = new MatchDescForm();
        List<String> matchPlayerNameList = new ArrayList<>();

        List<Games> games = matchDescService.getMatchDescDataMatchId(matchId);
        for(int i = 0; i < games.size(); i++){
            matchPlayerNameList.add(tftUtil.getTftPuiidToNameList(games.get(i).getPuuid(),apikey));
        }
        matchDescForm = matchDescForm.createMatchDescForm(games, matchPlayerNameList);
        matchDescData.put("matchData", matchDescForm);
        return matchDescData;
    }
}
