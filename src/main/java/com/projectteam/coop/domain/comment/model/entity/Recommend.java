package com.projectteam.coop.domain.comment.model.entity;

import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.post.model.entity.Post;
import com.projectteam.coop.global.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recommend", catalog = "coop")
public class Recommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECOMMEND_ID")
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
