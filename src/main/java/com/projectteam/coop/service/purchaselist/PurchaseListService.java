package com.projectteam.coop.service.purchaselist;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.exception.DuplicatePurchaseProductException;
import com.projectteam.coop.exception.NoPointException;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.repository.purchaselist.PurchaseListRepository;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class PurchaseListService {

    private final PurchaseListRepository purchaseListRepository;
    private final MemberRepository memberRepository;

    public Long addPurchaseList(Product product, Member member) {
        if (isNotDuplicatePurchase(member.getEmail(), product.getId()) && member.buyProduct(product.getPrice())) {
            PurchaseList purchaseList = PurchaseList.addPurchaseList(product, member);
            purchaseListRepository.addPurchaseList(purchaseList);
            memberRepository.updateMember(member);
            return purchaseList.getId();
        }
        throw new NoPointException("상품 구매에 필요한 포인트가 부족합니다.");
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<PurchaseList> findPurchaseList(MemberSessionDto loginMember, int offset, int size) {
        return purchaseListRepository.findPurchaseList(loginMember, offset, size);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<PurchaseList> memberPurchaseList(String email) {
        return purchaseListRepository.memberPurchaseList(email);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public PurchaseList memberApplyIcon(String email) {
        return purchaseListRepository.memberApplyIcon(email);
    }

    //상품(배경) 적용
    public void orderBackgroundApply(MemberSessionDto loginMember, Long id) {
        purchaseListRepository.orderBackgroundApply(loginMember, id);
    }

    //상품(아이콘) 적용
    public void orderIconApply(MemberSessionDto loginMember, Long id) {
        purchaseListRepository.orderIconApply(loginMember, id);
    }

    //상품(배경, 아이콘) 미적용
    public void orderUnapply(MemberSessionDto loginMember, Long id) {
        purchaseListRepository.orderUnapply(loginMember, id);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public boolean isNotDuplicatePurchase(String email, Long productId) {
        List<PurchaseList> purchaseLists = purchaseListRepository.memberPurchaseList(email);
        long purchaseCount = purchaseLists.stream()
                .filter(purchaseList -> purchaseList.getProduct().getId() == productId)
                .count();

        if (purchaseCount >= 1) {
            throw new DuplicatePurchaseProductException("이미 구매한 상품입니다.");
        }

        return true;
    }

    public int totalSize() {
        return purchaseListRepository.getTotalSize();
    }

}
