package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.model.entity.Summoner;
import com.projectteam.coop.tft.repository.SummonerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class SummonerService {

    private final SummonerRepository summonerRepository;

    public void setTftSummoner(Summoner summoner) {
        summonerRepository.addTftSummoner(summoner);
    }

    public List<Summoner> searchSummonerDataEntry(){
        return summonerRepository.findAll();
    }
}