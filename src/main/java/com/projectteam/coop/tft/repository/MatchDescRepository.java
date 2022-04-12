package com.projectteam.coop.tft.repository;

import com.projectteam.coop.tft.domain.MatchDesc;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatchDescRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    public String addTftDescData(MatchDesc matchDesc) {
        em.persist(matchDesc);
        return matchDesc.getMetaDateMatchId();
    }

    public boolean searchTftMatchIdData(String matchId){
        int size = em.createQuery("select m from MatchDesc m where m.metaDateMatchId = '" + matchId +"'", MatchDesc.class).getResultList().size();
        if (size > 0) {
            return false;
        }
        return true;
    }

    public List<MatchDesc> searchTftPlacementData(int placement){
        return em.createQuery("select m from MatchDesc m where m.placement = '" + placement + "'", MatchDesc.class).getResultList();
    }
}