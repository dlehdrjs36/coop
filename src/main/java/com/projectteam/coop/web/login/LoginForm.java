package com.projectteam.coop.web.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
