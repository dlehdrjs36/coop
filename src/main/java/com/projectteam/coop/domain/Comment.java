package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name ="COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_COMMENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    @Column(name = "COMMENT_GROUP")
    private Long group;

    @Column(name = "COMMENT_ORDER")
    private Long order;

    @Column(name = "COMMENT_DEPTH")
    private Long depth;

    private String content;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    private String nickname;

    private String updateMemberId;

    public void orderSort() {
        this.order = order + 1;
    }

    public void initGroup() {
        this.group = this.id;
    }

    public static Comment createComment(Post post, String password, String content, String nickname) {
        Comment comment = new Comment();
        comment.post = post;
        comment.password = password;
        comment.content = content;
        comment.nickname = nickname;
        comment.order = 0L;
        comment.depth = 0L;

        return comment;
    }

    public static Comment createComment(Post post, String password, String content, Member createMember) {
        Comment comment = new Comment();
        comment.post = post;
        comment.password = password;
        comment.content = content;
        comment.createMember = createMember;
        comment.order = 0L;
        comment.depth = 0L;

        return comment;
    }

    public static Comment createChildComment(String password, String content, String nickname, Comment parent) {
        Comment comment = new Comment();
        comment.password = password;
        comment.content = content;
        comment.nickname = nickname;
        comment.parent = parent;
        comment.group = parent.getGroup();
        comment.order = parent.getOrder() + 1L;
        comment.depth = parent.getDepth() + 1L;

        return comment;
    }

    public static Comment createChildComment(String password, String content, Member createMember, Comment parent) {
        Comment comment = new Comment();
        comment.password = password;
        comment.content = content;
        comment.createMember = createMember;
        comment.parent = parent;
        comment.group = parent.getGroup();
        comment.order = parent.getOrder() + 1L;
        comment.depth = parent.getDepth() + 1L;

        return comment;
    }

}
