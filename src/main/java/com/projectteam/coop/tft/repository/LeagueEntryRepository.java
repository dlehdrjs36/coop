package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.model.entity.LeagueEntry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional("mysqlTxManager")
public class LeagueEntryRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    public String addTftLeagueEntry(LeagueEntry leagueEntry) {
        em.persist(leagueEntry);
        return leagueEntry.getLeagueId();
    }

    public List<LeagueEntry> findAll() {
        return em.createQuery("select m from LeagueEntry m", LeagueEntry.class)
                .getResultList();
    }
}