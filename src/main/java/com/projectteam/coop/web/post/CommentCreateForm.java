package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentCreateForm {

    private Long commentId;

    private Long postId;

    @Size(min = 4, max = 8)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String content;

    private Long upperCommentId;

    private Long group;

    private Long order;

    private Long depth;

}
