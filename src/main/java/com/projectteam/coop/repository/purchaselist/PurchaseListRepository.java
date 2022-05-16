package com.projectteam.coop.repository.purchaselist;

import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.exception.NoAuthorityException;
import com.projectteam.coop.web.session.MemberSessionDto;
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
                .orElseGet(() -> null);;

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
    public void orderBackgroundApply(MemberSessionDto loginMember, Long id) {
//        em.createQuery("update PurchaseList p set p.status = 'UNAPPLY' where p.id <> :orderId");

        em.createQuery("select p from PurchaseList p where p.id <> :orderId and p.member.email = :email and p.product.type = 'BACKGROUND'", PurchaseList.class)
                .setParameter("orderId", id)
                .setParameter("email", loginMember.getEmail())
                .getResultList()
                .forEach((purchaseList) -> purchaseList.unapply());

        PurchaseList purchaseList = findPurchaseList(loginMember.getEmail(), id);
        if (purchaseList == null) {
            throw new NoAuthorityException("권한이 없습니다.");
        }
        purchaseList.apply();
    }

    //회원 구매 아이콘 상품 적용
    public void orderIconApply(MemberSessionDto loginMember, Long id) {
        em.createQuery("select p from PurchaseList p where p.id <> :orderId and p.member.email = :email and p.product.type = 'ICON'", PurchaseList.class)
                .setParameter("orderId", id)
                .setParameter("email", loginMember.getEmail())
                .getResultList()
                .forEach((purchaseList) -> purchaseList.unapply());

        PurchaseList purchaseList = findPurchaseList(loginMember.getEmail(), id);
        if (purchaseList == null) {
            throw new NoAuthorityException("권한이 없습니다.");
        }
        purchaseList.apply();
    }

    //회원 구매 배경, 아이콘 상품 미적용
    public void orderUnapply(MemberSessionDto loginMember, Long id) {
        PurchaseList purchaseList = findPurchaseList(loginMember.getEmail(), id);
        if (purchaseList == null) {
            throw new NoAuthorityException("권한이 없습니다.");
        }
        purchaseList.unapply();
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from PurchaseList p", PurchaseList.class)
                .getResultList()
                .size();

        return size;
    }
}
