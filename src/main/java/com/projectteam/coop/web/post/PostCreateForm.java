package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostCreateForm {

    private Long postId;
    private Long boardId = 1L; //TODO : 현재 게시판 기능 없음으로 임시로 게시판 테이블에서 생성하여 사용 중

    @NotBlank
    private String title;

    @Size(min = 4, max = 8)
    private String password;

    private String nickname;

    private String content;

    private Long upperPostId;

}
