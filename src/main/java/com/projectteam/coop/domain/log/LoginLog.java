package com.projectteam.coop.domain.log;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class LoginLog {

    @Id @GeneratedValue
    @Column(name = "LOGIN_ID")
    private Long id;

    private String email;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    protected LoginLog() { }

    public static LoginLog createLoginLog(String email) {
        LoginLog loginLog = new LoginLog();
        loginLog.email = email;
        loginLog.createDate = LocalDateTime.now();
        return loginLog;
    }
}
