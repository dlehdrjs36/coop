package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.Summoner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SummonerRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    public String addTftSummoner(Summoner summoner) {
        em.persist(summoner);
        return summoner.getAccountId();
    }

    public List<Summoner> findAll() {
        return em.createQuery("select m from Summoner m", Summoner.class)
                .getResultList();
    }
}