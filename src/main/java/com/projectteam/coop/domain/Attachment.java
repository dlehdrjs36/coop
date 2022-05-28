package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ATTACHMENT")
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
    @Column(name = "STATUS")
    private AttachmentStatus status;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "UPLOAD_NAME")
    private String uploadName;

    @Column(name = "PATH")
    private String path;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "DOWNLOAD_COUNT")
    private Integer downloadCount;

}
