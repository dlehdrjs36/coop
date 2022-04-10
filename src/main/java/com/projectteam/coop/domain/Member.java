package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private int point;

    private LocalDateTime createDate;

    @Column(name = "EMAIL_RECEPTION_AT")
    private Boolean emailReceptionType;

    //회원이 구매한 아이템에 대한 것들은 비즈니스적으로 의미있을 수 있는 정보이기 떄문에 활용성이 높다. 따라서 양방향 관계로 만들어준다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<PurchaseList> purchaseLists = new ArrayList<>();

    //연관관계 편의 메서드
//    public void addPurchaseList(PurchaseList purchaseList) {
//        this.purchaseLists.add(purchaseList);
//        purchaseList.addMember(this);
//    }

}