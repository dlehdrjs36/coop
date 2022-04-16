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

    public int totalSize() {
        return purchaseListRepository.getTotalSize();
    }

}
