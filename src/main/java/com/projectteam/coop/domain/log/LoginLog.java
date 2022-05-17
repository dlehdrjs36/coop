package com.projectteam.coop.domain.log;

import com.projectteam.coop.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class LoginLog extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "LOGIN_ID")
    private Long id;

    private String email;

    protected LoginLog() { }

    public static LoginLog createLoginLog(String email) {
        LoginLog loginLog = new LoginLog();
        loginLog.email = email;
        return loginLog;
    }
}
