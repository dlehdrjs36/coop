package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RECOMMEND")
public class Recommend extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "RECOMMED_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Recommend createRecommed(Post post, Member member) {
        Recommend recommend = new Recommend();
        recommend.post = post;
        recommend.member = member;

        return recommend;
    }
}
