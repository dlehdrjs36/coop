package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOARD")
public class Board extends BaseEntity {

    @Id @GeneratedValue
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

}
