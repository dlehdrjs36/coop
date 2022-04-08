package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PurchaseList {

    @Id @GeneratedValue
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
    private PurchaseListStatus status;

    //연관관계 편의 메서드용 Setter
//    public void addMember(Member member) {
//        this.member = member;
//    }

}
