package com.projectteam.coop.domain.log;

import com.projectteam.coop.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "login_log", catalog = "coop")
public class LoginLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGIN_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    public static LoginLog createLoginLog(String email) {
        LoginLog loginLog = new LoginLog();
        loginLog.email = email;
        return loginLog;
    }
}
