package com.projectteam.coop.web.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    @NotBlank
    private Long memberNo;

    @NotBlank
    private String memberId;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private Boolean emailReceptionType;
}
