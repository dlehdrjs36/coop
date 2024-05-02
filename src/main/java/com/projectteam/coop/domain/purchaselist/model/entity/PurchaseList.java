package com.projectteam.coop.domain.purchaselist.model.entity;

import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.product.model.entity.Product;
import com.projectteam.coop.domain.purchaselist.enums.PurchaseListStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchase_list", catalog = "coop")
public class PurchaseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASELIST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(name = "PRODUCT_PURCHASE_DATE")
    private LocalDateTime productPurchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PurchaseListStatus status;

    //연관관계 편의 메서드용
    public static PurchaseList addPurchaseList(Product product, Member member) {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.product = product;
        purchaseList.member = member;
        purchaseList.productPurchaseDate = LocalDateTime.now();
        purchaseList.status = PurchaseListStatus.ORDER;

        //양방향 연관관계 설정
        List<PurchaseList> memberPurchaseLists = member.getPurchaseLists();
        memberPurchaseLists.add(purchaseList);

        return purchaseList;
    }

    //상품 적용
    public void apply() {
        this.status = PurchaseListStatus.APPLY;
    }

    //상품 미적용
    public void unapply() {
        this.status = PurchaseListStatus.UNAPPLY;
    }
}
