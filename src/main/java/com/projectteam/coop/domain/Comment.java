package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(name = "STATUS")
    private Boolean status;

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

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "UPDATE_MEMBER_ID")
    private String updateMemberId;

    public void orderSort() {
        this.order = order + 1;
    }

    public void initGroup() {
        this.group = this.id;
    }

    public void delete() {
        this.status = Boolean.FALSE;
    }

    public static Comment createComment(Post post, String password, String content, String nickname) {
        Comment comment = new Comment();
        comment.post = post;
        comment.password = password;
        comment.status = Boolean.TRUE;
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
        comment.status = Boolean.TRUE;
        comment.content = content;
        comment.nickname = createMember.getName();
        comment.createMember = createMember;
        comment.order = 0L;
        comment.depth = 0L;

        return comment;
    }

    public static Comment createChildComment(Post post, String password, String content, String nickname, Comment parent) {
        Comment comment = new Comment();
        comment.post = post;
        comment.password = password;
        comment.status = Boolean.TRUE;
        comment.content = content;
        comment.nickname = nickname;
        comment.parent = parent;
        comment.group = parent.getGroup();
        comment.order = parent.getOrder() + 1L;
        comment.depth = parent.getDepth() + 1L;

        return comment;
    }

    public static Comment createChildComment(Post post, String password, String content, Member createMember, Comment parent) {
        Comment comment = new Comment();
        comment.post = post;
        comment.password = password;
        comment.status = Boolean.TRUE;
        comment.content = content;
        comment.nickname = createMember.getName();
        comment.createMember = createMember;
        comment.parent = parent;
        comment.group = parent.getGroup();
        comment.order = parent.getOrder() + 1L;
        comment.depth = parent.getDepth() + 1L;

        return comment;
    }


}
