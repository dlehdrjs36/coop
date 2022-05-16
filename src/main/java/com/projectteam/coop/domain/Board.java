package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    private Integer attachmentCapacityLimit;

    private String attachmentAllowExtension;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_MEMBER_ID")
    private Member createMember;

    private String updateMemberId;

}
