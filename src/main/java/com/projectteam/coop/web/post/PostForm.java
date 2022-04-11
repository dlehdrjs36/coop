package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    private Long postId;
    private String title;
    private String password;
    private String content;
}
