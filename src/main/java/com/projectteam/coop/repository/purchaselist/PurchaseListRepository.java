package com.projectteam.coop.repository.purchaselist;

import com.projectteam.coop.domain.PurchaseList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PurchaseListRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addPurchaseList(PurchaseList purchaseList) {
        em.persist(purchaseList);
        return purchaseList.getId();
    }

    //삭제
    public void removePurchaseList(Long id) {
        em.remove(id);
    }

    //목록 조회
    public List<PurchaseList> findPurchaseList(int offset, int size) {
        List<PurchaseList> findPurchaseList = em.createQuery("select p from PurchaseList p join fetch p.product", PurchaseList.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        return findPurchaseList;
    }

    //회원 구매목록 조회
    public List<PurchaseList> memberPurchaseList(String email) {
        List<PurchaseList> memberPurchaseList = em.createQuery("select p from PurchaseList p join fetch p.product join fetch p.member where p.member.email = :email", PurchaseList.class)
                .setParameter("email", email)
                .getResultList();

        return memberPurchaseList;
    }
    //단건 조회
    public Long orderApply(Long id) {
        PurchaseList purchaseList = em.find(PurchaseList.class, id);
        purchaseList.apply();

        return purchaseList.getId();
    }

    public Long orderUnApply(Long id) {
        PurchaseList purchaseList = em.find(PurchaseList.class, id);
        purchaseList.unapply();

        return purchaseList.getId();
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from PurchaseList p", PurchaseList.class)
                .getResultList()
                .size();

        return size;
    }
}
