package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostUpdateForm {
    @NotNull
    private Long postId;

    @NotEmpty
    private String title;

    @NotEmpty
    @Length(min = 4, max = 4)
    private String password;

    @NotEmpty
    private String nickname;

    private String content;

    private Long upperPostId;
}
