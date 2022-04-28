package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostCreateForm {

    private Long postId;

    @NotEmpty
    private String title;

    @Length(min = 4, max = 4)
    private String password;

    private String nickname;

    private String content;

    private Long upperPostId;

}
