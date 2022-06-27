package com.projectteam.coop.web.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
