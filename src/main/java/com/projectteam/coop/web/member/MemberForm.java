package com.projectteam.coop.web.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
