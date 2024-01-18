package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post", catalog = "coop")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PostStatus postStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "UPPER_POST_ID")
    private Post parent;

    @OneToMany(mappedBy = "parent")
    private List<Post> child = new ArrayList<>();

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "POST_GROUP")
    private Long group;

    @Column(name = "POST_ORDER")
    private Long order;

    @Column(name = "POST_DEPTH")
    private Long depth;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

    @Column(name = "RECOMMEND_COUNT")
    private Long recommendCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    @Column(name = "UPDATE_MEMBER_ID")
    private String updateMemberId;

    public static Post createReplyPost(String title, String password, String content, String nickname, Post parent, Board board) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.postStatus = PostStatus.Y;
        post.content = content;
        post.viewCount = 0;
        post.recommendCount = 0L;
        post.nickname = nickname;
        post.parent = parent;
        post.group = parent.getGroup();
        post.order = parent.getOrder();
        post.depth = parent.getDepth() + 1L;
        post.board = board;

        return post;
    }

    public static Post createReplyPost(String title, String password, String content, Member createMember, Post parent, Board board) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.postStatus = PostStatus.Y;
        post.content = content;
        post.viewCount = 0;
        post.recommendCount = 0L;
        post.nickname = createMember.getName();
        post.createMember = createMember;
        post.parent = parent;
        post.group = parent.getGroup();
        post.order = parent.getOrder();
        post.depth = parent.getDepth() + 1L;
        post.board = board;

        return post;
    }

    public static Post createPost(String title, String password, String content, String nickname, Board board) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.postStatus = PostStatus.Y;
        post.content = content;
        post.viewCount = 0;
        post.recommendCount = 0L;
        post.nickname = nickname;
        post.order = 0L;
        post.depth = 0L;
        post.board = board;

        return post;
    }

    public static Post createPost(String title, String password, String content, Member createMember, Board board) {
        Post post = new Post();
        post.title = title;
        post.password = password;
        post.postStatus = PostStatus.Y;
        post.content = content;
        post.viewCount = 0;
        post.recommendCount = 0L;
        post.nickname = createMember.getName();
        post.createMember = createMember;
        post.order = 0L;
        post.depth = 0L;
        post.board = board;

        return post;
    }

    public void changePost(Board board, String nickname, String password, String title, String content) {
        this.board = board;
        this.nickname = nickname;
        this.password = password;
        this.title = title;
        this.content = content;
    }

    //추천
    public void recommend(Long recommendCount) {
        this.recommendCount = recommendCount;
    }

    //조회수 증가
    public void addViewCount() {
        this.viewCount = this.viewCount + 1;
    }

    //답글 순서 정렬
    public void orderSort() {
        this.order = order + 1;
    }

    //게시물 그룹
    public void initGroup() {
        this.group = this.postId;
    }

}
