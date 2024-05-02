package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.model.entity.Summoner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SummonerRepository {

    @PersistenceContext(unitName = "mysqlJpa")
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