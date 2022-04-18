package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PostStatus postStatus;

    @Column(name = "UPPER_POST_ID")
    private Long upperPostId;

    private String password;

    private String title;

    @Lob
    private String content;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

    @Column(name = "RECOMMEND_COUNT")
    private Integer recommendCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    @Column(name = "UPDATE_MEMBER_ID")
    private String updateMemberId;


    public static Post createPost(String title, String password, String content) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.postStatus = PostStatus.Y;
        post.content = content;
        post.viewCount = 0;
        post.recommendCount = 0;

        return post;
    }

    public void changePost(Board board, PostStatus postStatus, Long upperPostId, String password, String title, String content, Integer viewCount, Integer recommendCount) {
        this.board = board;
        this.postStatus = postStatus;
        this.password = password;
        this.upperPostId = upperPostId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.recommendCount = recommendCount;
    }

    public void addViewCount() {
        this.viewCount = this.viewCount + 1;
    }

}
