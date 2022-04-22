package com.projectteam.coop.web.tft;

import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.tft.service.MatchDescService;
import com.projectteam.coop.util.TftUtil;
import com.projectteam.coop.web.menu.summonerPageForm.MatchDescForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final String apkKey = "RGAPI-2b1f1f37-9eb6-4ff4-a602-796ff6cb654c";
    private final MatchDescService matchDescService;

    @GetMapping(value="/record")
    public String doGet(){
        return "redirect:/";
    }

    @PostMapping(value="/record")
    public String updateUserRecord(HttpServletRequest request){
        String puuid = request.getParameter("puuid");
        String name = request.getParameter("name");
        matchDescService.addMatchDesc(puuid,apkKey);

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
            matchPlayerNameList.add(tftUtil.getTftPuiidToNameList(matchDescs.get(i).getPuuid(),apkKey));
        }
        matchDescForm = matchDescForm.createMatchDescForm(matchDescs, matchPlayerNameList);
        matchData.put("matchData", matchDescForm);
        return matchData;
    }
}
