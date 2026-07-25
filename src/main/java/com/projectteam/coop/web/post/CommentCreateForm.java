package com.projectteam.coop.web.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
