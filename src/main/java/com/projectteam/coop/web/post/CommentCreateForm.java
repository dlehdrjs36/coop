package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CommentCreateForm {

    private Long commentId;

    private Long postId;

    @Length(min = 4, max = 4)
    private String password;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String content;

    private Long upperCommentId;

    private Long group;

    private Long order;

    private Long depth;

}
