package com.projectteam.coop.util;

import com.projectteam.coop.tft.domain.LeagueEntry;
import com.projectteam.coop.tft.domain.MatchDescDTO.MatchDescDTO;
import com.projectteam.coop.tft.domain.Summoner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TftUtil {

    /*
     * getTftSummoner 닉네임으로 아이디 정보 확인 함수
     * */
    public Summoner getTftSummoner(String summonerId, String apikey) {
        String uri = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/" + summonerId;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        ResponseEntity<Summoner> SummonerData = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), Summoner.class);

        return SummonerData.getBody();
    }

    /*
     * getTftSummoner 닉네임으로 아이디 정보 확인 함수
     * */
    public List<String> getTftMatchId(String puuid, int count, String apikey) {
        String uri = "https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/" + puuid + "/ids?count="+ count;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        List<String> tftMatchIdList = restTemplate.getForObject(uriComponents.toUriString(), List.class);
        return tftMatchIdList;
    }

    /*
     * getTftMatchDesc 매치 데이터 수집 함수
     * MatchId 찾는 매치 아이디
     * */
    public MatchDescDTO getTftMatchDesc(String MatchId, String apikey) {
        String uri = "https://asia.api.riotgames.com/tft/match/v1/matches/" + MatchId;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        ResponseEntity<MatchDescDTO> MatchData = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), MatchDescDTO.class);

        return MatchData.getBody();
    }

    public List<LeagueEntry> getTftLeagueEntry(String tier, String division, int page, String apikey) {
        String uri = "https://kr.api.riotgames.com/tft/league/v1/entries/" + tier + "/" + division + "?page=" + page;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        ResponseEntity<List<LeagueEntry>> LeagueEntryData = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<LeagueEntry>>() {});

        return LeagueEntryData.getBody();
    }

    public List<LeagueEntry> getTftSummonerEntry(String summonerId, String apikey) {
        String uri = "https://kr.api.riotgames.com/tft/league/v1/entries/by-summoner/" + summonerId;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        ResponseEntity<List<LeagueEntry>> LeagueEntryData = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<LeagueEntry>>() {});

        return LeagueEntryData.getBody();
    }

    /*
    * 
    * puiid 를 사용해서 일반 닉네임 리턴이 필요한 경우
    * 
    * */
    public String getTftPuiidToNameList(String puuid, String apikey){
        String uri = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-puuid/" + puuid;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("api_key", apikey)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus code = response.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                }
                return false;
            }
        });

        ResponseEntity<Summoner> SummonerData = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), Summoner.class);

        return SummonerData.getBody().getName();
    }


    /*
    * Riot Api상에서는 증강체는 구분을 해주지 않기 때문에 티어별 패키지를 구분해서 사용할 경우 체크하는 함수는 필요
    * */
    public String getAugmentTier(String[] augments){
        final ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/static/img/tft/augment/");
        String augmentTier = "";
        String augmentPath = "";
        int i, j;

        try {
            augmentPath = resource.getURL().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(i=0; i<augments.length; i++){
            for(j=1; j<4; j++){
                File file1 = new File(augmentPath + "tier" + j + "/" + augments[i].substring(13)+".png");
                if(file1.exists()){
                    augmentTier += String.valueOf(j) + '|';
                }
            }
        }

        if(!augmentTier.equals("")) {
            augmentTier = augmentTier.substring(0, augmentTier.length() - 1);
        }

        return augmentTier;
    }

    public String getTftItemMappingName(String itemName){
        if(itemName.equals("TFT_Item_TitanicHydra")){
            return "Zz'rot Portal";
        }else if(itemName.equals("TFT_Item_SeraphsEmbrace")){
            return "BlueBuff";
        }else if(itemName.equals("TFT_Item_RedBuff")){
            return "SunfireCape";
        }else if(itemName.equals("TFT_Item_PowerGauntlet")){
            return "BansheesClaw";
        }else if(itemName.equals("TFT_Item_GuardianAngel")){
            return "EdgeofNight";
        }else if(itemName.equals("TFT_Item_MadredsBloodrazor")){
            return "GiantSlayer";
        }else if(itemName.indexOf("EmblemItem") != -1){
            return itemName.substring(10, itemName.length()-4);
        }else if(itemName.indexOf("TFT4_Item_Ornn") != -1){
            return itemName.substring(14);
        }else{
            return itemName.substring(9);
        }
    }

    public String getGameDatetime(long datetime){
        long diff, diffDay, diffHour, diffMinute;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        Date gameDate = null;
        String gameDatetime = dateFormat.format(datetime);

        try {
            gameDate = dateFormat.parse(gameDatetime);
        }catch (Exception e){

        }

        diff = date.getTime() - gameDate.getTime();
        TimeUnit timeDay = TimeUnit.DAYS;
        TimeUnit timeHour = TimeUnit.HOURS;
        TimeUnit timeMinute = TimeUnit.MINUTES;
        diffDay = timeDay.convert(diff, TimeUnit.MILLISECONDS);
        diffHour = timeHour.convert(diff, TimeUnit.MILLISECONDS);
        diffMinute = timeMinute.convert(diff, TimeUnit.MILLISECONDS);

        if(diffHour > 24){
            return String.valueOf(diffDay) + "일 전";
        }else if( diffMinute > 60 ){
            return String.valueOf(diffHour) + "시간 전";
        }else{
            return String.valueOf(diffMinute) + "분 전";
        }
    }
}