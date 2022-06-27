package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostCreateForm {

    private Long postId;

    @NotBlank
    private String title;

    @Size(min = 4, max = 8)
    private String password;

    private String nickname;

    private String content;

    private Long upperPostId;

}
