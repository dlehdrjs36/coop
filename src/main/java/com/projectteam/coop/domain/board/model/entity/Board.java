package com.projectteam.coop.domain.board.model.entity;

import com.projectteam.coop.domain.board.enums.BoardStatus;
import com.projectteam.coop.domain.board.enums.BoardType;
import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.global.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board", catalog = "coop")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private BoardType type;

    @Column(name = "ATTACHMENT_CAPACITY_LIMIT")
    private Integer attachmentCapacityLimit;

    @Column(name = "ATTACHMENT_ALLOW_EXTENSION")
    private String attachmentAllowExtension;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BoardStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    @Column(name = "UPDATE_MEMBER_ID")
    private String updateMemberId;

    //TODO : 게시판 타입별 생각해서 구현하기
    public static Board createBoard() {
        Board board = new Board();
        board.status = BoardStatus.Y;

        return board;
    }
}
