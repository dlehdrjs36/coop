package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.MatchDesc;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatchDescRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    public String addTftDescData(MatchDesc matchDesc) {
        em.persist(matchDesc);
        return matchDesc.getMetadateMatchId();
    }

    public boolean isTftMatchIdData(String matchId){
        int size = em.createQuery("select m from MatchDesc m where m.metadateMatchId = '" + matchId +"'", MatchDesc.class).getResultList().size();
        if (size > 0) {
            return false;
        }
        return true;
    }

    public List<MatchDesc> searchTftMatchIdData(String matchId){
        return em.createQuery("select m from MatchDesc m where m.metadateMatchId = '" + matchId +"' order by m.placement", MatchDesc.class).getResultList();
    }

    public List<MatchDesc> searchTftPlacementData(int placement){
        return em.createQuery("select m from MatchDesc m where m.placement = '" + placement + "'", MatchDesc.class).getResultList();
    }

    public List<MatchDesc> searchTftPuuidData(String puuid){
        return em.createQuery("select m from MatchDesc m where m.puuid = '" + puuid + "' order by m.metadateMatchId desc ", MatchDesc.class).getResultList();
    }
}