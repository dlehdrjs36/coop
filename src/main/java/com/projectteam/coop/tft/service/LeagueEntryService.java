package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.model.entity.LeagueEntry;
import com.projectteam.coop.tft.repository.LeagueEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class LeagueEntryService {

    private final LeagueEntryRepository leagueEntryRepository;

    public void addTftLeagueEntry(List<LeagueEntry> LeagueEntryData) {
        for (LeagueEntry leagueEntryDatum : LeagueEntryData) {
            leagueEntryRepository.addTftLeagueEntry(leagueEntryDatum);
        }
    }

    public List<LeagueEntry> searchTftLeagueEntry(){
        return leagueEntryRepository.findAll();
    }
}