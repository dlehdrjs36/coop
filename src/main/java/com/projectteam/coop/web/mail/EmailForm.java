package com.projectteam.coop.web.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailForm {

    @Email
    @NotBlank
    private String email;
}