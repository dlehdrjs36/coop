package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Recommed extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "RECOMMED_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    protected Recommed() {}

    public static Recommed createRecommed(Post post, Member member) {
        Recommed recommed = new Recommed();
        recommed.post = post;
        recommed.member = member;

        return recommed;
    }
}
