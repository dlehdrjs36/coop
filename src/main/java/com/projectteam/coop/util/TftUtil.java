package com.projectteam.coop.util;

import com.projectteam.coop.tft.domain.LeagueEntry;
import com.projectteam.coop.tft.domain.MatchDescDTO;
import com.projectteam.coop.tft.domain.Summoner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class TftUtil {

    /*
     * getTftSummoner 닉네임으로 아이디 정보 확인 함수
     * */
    public Summoner getTftSummoner(String summonerId, String apikey) {

        String uri = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/" + summonerId;
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
}