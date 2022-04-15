package com.projectteam.coop.tft.repository;

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

    public Synergy findSynergy(String synergyName) {
        return em.createQuery("select m from Synergy m where m.traitsName = :traitsName", Synergy.class)
                .setParameter("traitsName", synergyName)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<Synergy> findAll() {
        return em.createQuery("select m from Synergy m", Synergy.class)
                .getResultList();
    }

    public List<String> findAllSynergyName(){
        return em.createQuery("select m.traitsName from Synergy m order by m.traitsNameKr", String.class)
                .getResultList();
    }

    public List<String> findAllSynergyNameKr(){
        return em.createQuery("select m.traitsNameKr from Synergy m order by m.traitsNameKr", String.class)
                .getResultList();
    }

    public String updateWinRateUnits(String winRate, String units, String augments,String traitsName){
        Synergy synergy = em.find(Synergy.class, traitsName);
        synergy.changeSynergy(synergy, winRate, units, augments);
        return synergy.getTraitsName();
    }
}