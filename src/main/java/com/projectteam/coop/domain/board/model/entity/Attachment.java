package com.projectteam.coop.domain.board.model.entity;

import com.projectteam.coop.domain.board.enums.AttachmentStatus;
import com.projectteam.coop.domain.post.model.entity.Post;
import com.projectteam.coop.global.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attachment", catalog = "coop")
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
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
