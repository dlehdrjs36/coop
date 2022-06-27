package com.projectteam.coop.web.mail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EmailForm {

    @Email
    @NotBlank
    private String email;
}