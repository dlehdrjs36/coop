package com.projectteam.coop.controller;

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
