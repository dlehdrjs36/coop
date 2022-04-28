package com.projectteam.coop.web.post;

import com.projectteam.coop.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    private String nickname;

    private String content;

    private Member createMember;

    private Long upperPostId;
}
