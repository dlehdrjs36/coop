package com.projectteam.coop.domain;

import com.projectteam.coop.web.member.MemberForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SALT")
    private String salt;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private MemberType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MemberStatus status;

    @Column(name = "POINT")
    private int point;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "EMAIL_RECEPTION_AT")
    private Boolean emailReceptionType;

    //회원이 구매한 아이템에 대한 것들은 비즈니스적으로 의미있을 수 있는 정보이기 떄문에 활용성이 높다. 따라서 양방향 관계로 만들어준다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<PurchaseList> purchaseLists = new ArrayList<>();

    //회원가입
    public static Member createMember(String memberId, String email, String name, String password, String salt, Boolean emailReceptionType) {
        Member member = new Member();
        member.memberId = memberId;
        member.email = email;
        member.name = name;
        member.password = password;
        member.salt = salt;
        member.type = MemberType.COMMON;
        member.status = MemberStatus.ACTIVE;
        member.point = 0;
        member.emailReceptionType = emailReceptionType;

        return member;
    }

    //회원가입
    public static Member createMember(String email, String name, String password, String salt, Boolean emailReceptionType) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;
        member.salt = salt;
        member.type = MemberType.COMMON;
        member.status = MemberStatus.ACTIVE;
        member.point = 0;
        member.emailReceptionType = emailReceptionType;

        return member;
    }


    public void changePassword(String password) {
        this.password = password;
    }

    public void changeMember(Member member) {
        this.name = member.getName();
        this.password = member.getPassword();
        this.point = member.getPoint();
        this.emailReceptionType = member.getEmailReceptionType();
    }

    public void changeMember(MemberForm member) {
        this.name = member.getName();
        this.password = member.getPassword();
        this.emailReceptionType = member.getEmailReceptionType();
    }

    public void minusPoint(int point) {
        this.point -= point;
    }

    public void addPoint() {
        this.point += 10;
    }

    //상품 구매
    public boolean buyProduct(int price) {
        if(this.point - price >= 0) {
            this.point -= price;
            return true;
        }
        return false;
    }

}
