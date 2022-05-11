package com.projectteam.coop.service.purchaselist;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.PurchaseList;
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

    public void addPurchaseList(Product product, Member member) {
        PurchaseList purchaseList = PurchaseList.addPurchaseList(product, member);
        purchaseListRepository.addPurchaseList(purchaseList);
        memberRepository.updateMember(member);
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

    public int totalSize() {
        return purchaseListRepository.getTotalSize();
    }

}
