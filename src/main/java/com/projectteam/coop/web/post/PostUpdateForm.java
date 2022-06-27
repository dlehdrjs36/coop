package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostUpdateForm {
    @NotBlank
    private Long postId;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 4, max = 8)
    private String password;

    private String nickname;

    private String content;

    private Member createMember;

    private Long upperPostId;
}
