package com.projectteam.coop.tft.repository;

import com.projectteam.coop.sample.jpa.domain.JpaSample;
import com.projectteam.coop.tft.domain.Summoner;
import com.projectteam.coop.tft.domain.Synergy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SynergyRepository {
    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    public String addSynergy(Synergy synergy) {
        em.persist(synergy);
        return synergy.getTraitsName();
    }

    public List<Synergy> findSynergy(String synergyName) {
        return em.createQuery("select m from Synergy m where m.traitsName = '" + synergyName +"'", Synergy.class).getResultList();
    }

    public List<Synergy> findAll() {
        return em.createQuery("select m from Synergy m", Synergy.class)
                .getResultList();
    }

    public String updateWinRateUnits(String winRate, String units, String Augments,String traitsName){
        Synergy synergy = em.find(Synergy.class, traitsName);
        synergy.setWinRate(winRate);
        synergy.setUsedAugments(Augments);
        synergy.setUsedAnotherUnit(units);
        return synergy.getTraitsName();
    }
}