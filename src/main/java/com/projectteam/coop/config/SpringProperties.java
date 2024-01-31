package com.projectteam.coop.config;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SpringProperties {
    private String url;
    private String DriverClassName;
    private String username;
    private String password;
}
