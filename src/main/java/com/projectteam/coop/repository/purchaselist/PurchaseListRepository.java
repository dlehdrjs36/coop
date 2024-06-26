package com.projectteam.coop.repository.purchaselist;

import com.projectteam.coop.domain.purchaselist.model.entity.PurchaseList;
import com.projectteam.coop.web.session.MemberSessionDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseListRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    //등록
    public Long addPurchaseList(PurchaseList purchaseList) {
        em.persist(purchaseList);
        return purchaseList.getId();
    }

    //목록 조회
    public List<PurchaseList> findPurchaseList(MemberSessionDto loginMember, int offset, int size) {
        List<PurchaseList> findPurchaseList = em.createQuery("select p from PurchaseList p join fetch p.product where p.member.email = :email", PurchaseList.class)
                .setParameter("email", loginMember.getEmail())
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

    //회원 구매목록 단건 조회
    public PurchaseList findPurchaseList(Long id) {
        PurchaseList purchaseList = em.find(PurchaseList.class, id);
        return purchaseList;
    }

    //회원 적용 아이콘 조회
    public PurchaseList memberApplyIcon(String email) {
        PurchaseList result = em.createQuery("select p from PurchaseList p join fetch p.product join fetch p.member where p.member.email = :email and p.status = 'APPLY' and p.product.type = 'ICON'", PurchaseList.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return result;
    }

    //회원 구매목록 단건 조회
    public PurchaseList findPurchaseList(String email, Long id) {
        PurchaseList purchaseList = em.createQuery("select p from PurchaseList p where p.id = :orderId and p.member.email = :email", PurchaseList.class)
                .setParameter("orderId", id)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return purchaseList;
    }

    //회원 구매 배경 상품 적용
    public List<PurchaseList> memberBackgroundList(MemberSessionDto loginMember, Long id) {
        List<PurchaseList> resultList = em.createQuery("select p from PurchaseList p where p.id <> :orderId and p.member.email = :email and p.product.type = 'BACKGROUND'", PurchaseList.class)
                .setParameter("orderId", id)
                .setParameter("email", loginMember.getEmail())
                .getResultList();

        return resultList;
    }

    //회원 구매 아이콘 상품 적용
    public List<PurchaseList> memberIconList(MemberSessionDto loginMember, Long id) {
        List<PurchaseList> resultList = em.createQuery("select p from PurchaseList p where p.id <> :orderId and p.member.email = :email and p.product.type = 'ICON'", PurchaseList.class)
                .setParameter("orderId", id)
                .setParameter("email", loginMember.getEmail())
                .getResultList();

        return resultList;
    }

    //회원 구매 배경, 아이콘 상품 미적용
    public PurchaseList orderUnapply(MemberSessionDto loginMember, Long id) {
        PurchaseList purchaseList = findPurchaseList(loginMember.getEmail(), id);
        return purchaseList;
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from PurchaseList p", PurchaseList.class)
                .getResultList()
                .size();

        return size;
    }
}
