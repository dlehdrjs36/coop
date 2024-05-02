package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.model.entity.Games;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchDescRepository {

    private final EntityManager em;

    public MatchDescRepository(EntityManager em) {
        this.em = em;
    }

    public String addTftDescData(Games games) {
        em.persist(games);
        return games.getMatchId();
    }

    public boolean isTftMatchIdData(String matchId){
        int size = em.createQuery("select g from Games g where g.matchId = :matchId", Games.class)
                .setParameter("matchId", matchId)
                .getResultList().size();

        return size > 0 ? false : true;
    }

    public List<Games> searchTftMatchIdData(String matchId){
        return em.createQuery("select g from Games g where g.matchId =:matchId order by g.placement", Games.class)
                .setParameter("matchId", matchId)
                .getResultList();
    }

    public List<Games> searchTftPlacementData(int placement){
        return em.createQuery("select g from Games g where g.placement =:placement", Games.class)
                .setParameter("placement", placement)
                .getResultList();
    }

    public List<Games> searchTftPuuidData(String puuid){
        List<Games> puuid1 = em.createQuery("select g from Games g where g.puuid =:puuid order by g.matchId desc", Games.class)
                .setParameter("puuid", puuid)
                .getResultList();

        return puuid1;
    }
}