package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

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

    private Long upperPostId;

    private String password;

    private String title;

    @Lob
    private String content;

    private Integer viewCount;

    private Integer recommendCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    private String updateMemberId;


    public static Post createPost(String title, String password, String content) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.content = content;

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

}
