package com.projectteam.coop.web.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Boolean emailReceptionType;
}
