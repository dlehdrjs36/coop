package com.projectteam.coop.service.purchaselist;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.repository.purchaselist.PurchaseListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class PurchaseListService {

    private final PurchaseListRepository purchaseListRepository;

    public Long addPurchaseList(Product product, Member member) {
        PurchaseList purchaseList = PurchaseList.addPurchaseList(product, member);
        return purchaseListRepository.addPurchaseList(purchaseList);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<PurchaseList> findPurchaseList(int offset, int size) {
        return purchaseListRepository.findPurchaseList(offset, size);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<PurchaseList> memberPurchaseList(String email) {
        return purchaseListRepository.memberPurchaseList(email);
    }

    //상품 적용
    public void orderApply(Long id) {
        purchaseListRepository.orderApply(id);
    }

    //상품 미적용
    public void orderUnApply(Long id) {
        purchaseListRepository.orderUnApply(id);
    }

    public int totalSize() {
        return purchaseListRepository.getTotalSize();
    }

}
