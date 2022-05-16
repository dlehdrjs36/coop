package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ATTACHMENT_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Enumerated(EnumType.STRING)
    private AttachmentStatus status;

    private String originName;

    private String uploadName;

    private String path;

    private Integer capacity;

    private Integer downloadCount;

}
