package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.member.model.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
