package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

    protected Recommend() {}

    public static Recommend createRecommed(Post post, Member member) {
        Recommend recommend = new Recommend();
        recommend.post = post;
        recommend.member = member;

        return recommend;
    }
}
