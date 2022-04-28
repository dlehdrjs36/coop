package com.projectteam.coop.repository.recommed;

import com.projectteam.coop.domain.Recommend;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RecommendRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addRecommend(Recommend recommend) {
        em.persist(recommend);
        return recommend.getId();
    }

    //단건 조회
    public Recommend findRecommend(Long id) {
        return em.find(Recommend.class, id);
    }
    //삭제
    public void removeRecommend(Long id) {
        Recommend recommend = em.find(Recommend.class, id);
        em.remove(recommend);
    }

    //단건 조회
    public Recommend findMemberRecommend(Long memberId, Long postId) {
        Recommend result = em.createQuery("select r from Recommend r where r.member.id = :memberId and r.post.postId = :postId", Recommend.class)
                .setParameter("memberId", memberId)
                .setParameter("postId", postId)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return result;
    }

    //게시물 추천수 조회
    public Long getPostRecommendCount(Long postId) {
        Long result = em.createQuery("select count(r.post.postId) from Recommend r where r.post.postId = :postId", Long.class)
                .setParameter("postId", postId)
                .getSingleResult();

        return result;
    }

}
