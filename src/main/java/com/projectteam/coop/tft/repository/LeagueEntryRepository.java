package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.LeagueEntry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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