package com.projectteam.coop.repository.recommed;

import com.projectteam.coop.domain.Recommed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RecommedRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addRecommed(Recommed recommed) {
        em.persist(recommed);
        return recommed.getId();
    }

    //단건 조회
    public Recommed findRecommed(Long id) {
        return em.find(Recommed.class, id);
    }
    //삭제
    public void removeRecommed(Long id) {
        Recommed recommed = em.find(Recommed.class, id);
        em.remove(recommed);
    }

    //단건 조회
    public Recommed findMemberRecommed(Long memberId, Long postId) {
        Recommed result = em.createQuery("select r from Recommed r where r.member.id = :memberId and r.post.postId = :postId", Recommed.class)
                .setParameter("memberId", memberId)
                .setParameter("postId", postId)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return result;
    }

    //단건 조회
    public Long getPostRecommedCount(Long postId) {
        Long result = em.createQuery("select count(r.post.postId) from Recommed r where r.post.postId = :postId", Long.class)
                .setParameter("postId", postId)
                .getSingleResult();

        return result;
    }

}
