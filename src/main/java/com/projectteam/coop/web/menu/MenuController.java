package com.projectteam.coop.web.menu;

import com.projectteam.coop.tft.domain.LeagueEntry;
import com.projectteam.coop.tft.domain.MatchDesc;
import com.projectteam.coop.tft.domain.Summoner;
import com.projectteam.coop.tft.domain.Synergy;
import com.projectteam.coop.tft.service.MatchDescService;
import com.projectteam.coop.tft.service.SynergyService;
import com.projectteam.coop.util.TftUtil;
import com.projectteam.coop.web.menu.summonerPageForm.MatchDescForm;
import com.projectteam.coop.web.menu.summonerPageForm.SummonerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final SynergyService synergyService;
    private final MatchDescService matchDescService;
    private final TftUtil tftUtil = new TftUtil();
    @Value("${coop.riot.apiKey}")
    private String apikey;

    @GetMapping(value = "/tft/augmentsList")
    public String getAugmentsListPage(Model model) {
        return "/templates/tft/augmentsList";
    }

    @GetMapping(value = "/tft/championList")
    public String getChampionListPage(Model model) {
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/tft/championList/{championName}")
    public String searchChampion(@PathVariable String championName, Model model) {
        if(championName != "") {
            model.addAttribute(championName, championName);
        } else {
            model.addAttribute(championName, "");
        }
        return "/templates/tft/championList";
    }

    @GetMapping(value = "/tft/synergyList")
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

    @GetMapping("/tft/synergyList/{synergyName}")
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

    @GetMapping("/tft/combination")
    public String getCombinationPage(Model model){
        return "/templates/tft/combination";
    }

    @GetMapping("/tft/guide")
    public String getGuidePage(Model model){
        return "/templates/tft/guide";
    }

    @GetMapping("/tft/summoner/{summonerName}")
    public String getSummonerPage(@PathVariable String summonerName, Model model) {
        Summoner summoner;
        List<LeagueEntry> leagueEntry;
        SummonerForm summonerForm = new SummonerForm();
        List<MatchDesc> matchDescs;
        List<MatchDesc> sommonerMatchDescs;
        List<String> matchPlayerNameList = new ArrayList<>();
        MatchDescForm matchDescForm = new MatchDescForm();
        int i;

        try {
            summoner = tftUtil.getTftSummoner(summonerName, apikey);
            leagueEntry = tftUtil.getTftSummonerEntry(summoner.getId(), apikey);
            summonerForm.setProfileIconId(summoner.getProfileIconId());
            summonerForm.setName(summoner.getName());
            summonerForm.setPuuid(summoner.getPuuid());
            summonerForm.setSummonerLevel(summoner.getSummonerLevel());

            summonerForm.setTier(leagueEntry.get(0).getTier());
            summonerForm.setRank(leagueEntry.get(0).getRank());
            summonerForm.setLeaguePoints(leagueEntry.get(0).getLeaguePoints());
            summonerForm.setWins(leagueEntry.get(0).getWins());
            summonerForm.setLosses(leagueEntry.get(0).getLosses());
            model.addAttribute("summonerForm", summonerForm);

            sommonerMatchDescs = matchDescService.getMatchDescDataPuuid(summoner.getPuuid());
            if(sommonerMatchDescs.size() == 0){
            }else{
                matchDescs = matchDescService.getMatchDescDataMatchId(sommonerMatchDescs.get(0).getMetadateMatchId());
                for(i=0; i<matchDescs.size(); i++){
                    matchPlayerNameList.add(tftUtil.getTftPuiidToNameList(matchDescs.get(i).getPuuid(),apikey));
                }
                matchDescForm = matchDescForm.createMatchDescForm(matchDescs, matchPlayerNameList);
            }
        }catch (Exception e){
            model.addAttribute("searchName",summonerName);
            return "/templates/tft/summonerSearchError";
        }

        model.addAttribute("sommonerMatchDescs", sommonerMatchDescs);
        model.addAttribute("matchDescForm", matchDescForm);

        return "/templates/tft/summoner";
    }
}