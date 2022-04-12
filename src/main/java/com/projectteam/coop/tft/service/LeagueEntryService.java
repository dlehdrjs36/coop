package com.projectteam.coop.tft.service;

import com.projectteam.coop.tft.domain.LeagueEntry;
import com.projectteam.coop.tft.repository.LeagueEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class LeagueEntryService {

    private final LeagueEntryRepository leagueEntryRepository;

    public void addTftLeagueEntry(List<LeagueEntry> LeagueEntryData) {
        for(int i=0; i<LeagueEntryData.size(); i++) {
            leagueEntryRepository.addTftLeagueEntry(LeagueEntryData.get(i));
        }
    }

    public List<LeagueEntry> searchTftLeagueEntry(){
        return leagueEntryRepository.findAll();
    }
}