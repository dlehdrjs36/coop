package com.projectteam.coop.service.purchaselist;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.exception.DuplicatePurchaseProductException;
import com.projectteam.coop.exception.NoAuthorizationException;
import com.projectteam.coop.exception.NoPointException;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.purchaselist.PurchaseListRepository;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class PurchaseListService {

    private final PurchaseListRepository purchaseListRepository;
    private final MemberRepository memberRepository;

    public Long addPurchaseList(Product product, Member member) {
        if (isDuplicatePurchase(member.getEmail(), product.getId())) {
            throw new DuplicatePurchaseProductException("이미 구매한 상품입니다.");
        }
        if (isEnoughPoints(product, member)) {
            PurchaseList purchaseList = PurchaseList.addPurchaseList(product, member);
            purchaseListRepository.addPurchaseList(purchaseList);
            memberRepository.updateMember(member);
            return purchaseList.getId();
        }
        throw new NoPointException("상품 구매에 필요한 포인트가 부족합니다.");
    }

    private boolean isEnoughPoints(Product product, Member member) {
        return member.buyProduct(product.getPrice());
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public boolean isDuplicatePurchase(String email, Long productId) {
        List<PurchaseList> purchaseLists = purchaseListRepository.memberPurchaseList(email);
        long purchaseCount = purchaseLists.stream()
                .filter(purchaseList -> isSameProduct(productId, purchaseList))
                .count();

        return purchaseCount >= 1;
    }

    private boolean isSameProduct(Long productId, PurchaseList purchaseList) {
        return purchaseList.getProduct().getId() == productId;
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<PurchaseList> findPurchaseList(MemberSessionDto loginMember, int offset, int size) {
        return purchaseListRepository.findPurchaseList(loginMember, offset, size);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<PurchaseList> memberPurchaseList(String email) {
        return purchaseListRepository.memberPurchaseList(email);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public PurchaseList memberApplyIcon(String email) {
        return purchaseListRepository.memberApplyIcon(email);
    }

    //상품(배경) 적용
    public void orderBackgroundApply(MemberSessionDto loginMember, Long id) {
        List<PurchaseList> purchaseLists = purchaseListRepository.memberBackgroundList(loginMember, id);
        purchaseLists.forEach(purchaseList -> purchaseList.unapply());

        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(loginMember.getEmail(), id);
        if (purchaseList == null) {
            throw new NoAuthorizationException("권한이 없습니다.");
        }
        purchaseList.apply();
    }

    //상품(아이콘) 적용
    public void orderIconApply(MemberSessionDto loginMember, Long id) {
        List<PurchaseList> purchaseLists = purchaseListRepository.memberIconList(loginMember, id);
        purchaseLists.forEach(purchaseList -> purchaseList.unapply());

        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(loginMember.getEmail(), id);
        if (purchaseList == null) {
            throw new NoAuthorizationException("권한이 없습니다.");
        }
        purchaseList.apply();
    }

    //상품(배경, 아이콘) 미적용
    public void orderUnapply(MemberSessionDto loginMember, Long id) {
        PurchaseList purchaseList = purchaseListRepository.orderUnapply(loginMember, id);
        if (purchaseList == null) {
            throw new NoAuthorizationException("권한이 없습니다.");
        }
        purchaseList.unapply();
    }

    public int totalSize() {
        return purchaseListRepository.getTotalSize();
    }

}
