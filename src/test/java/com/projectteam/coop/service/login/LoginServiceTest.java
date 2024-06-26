package com.projectteam.coop.service.login;

import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.domain.login.service.LoginService;
import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.member.service.MemberService;
import com.projectteam.coop.repository.log.LoginRepository;
import com.projectteam.coop.util.SecurityUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@ActiveProfiles("local")
@SpringBootTest(
        properties =
                "spring.config.import="
                        + "optional:../properties/local_security/coop_server_local.yml,"
                        + "optional:../properties/coop_server.yml",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(transactionManager = "mysqlTxManager")
class LoginServiceTest {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginRepository loginRepository;

    @Test
    @DisplayName("일별 로그인 시 포인트 미증가")
    void noAddPoint() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("aaaa","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long addMemberId = memberService.addMember(member);
        em.flush();

        assertEquals(member, memberService.findMember(addMemberId));
        assertEquals(0, member.getPoint());
        assertEquals(0, loginRepository.findLoginLog(member));

        loginService.addPoint(member);
        em.flush();

        Member changedMember = memberService.findMember(addMemberId);
        assertEquals(0, changedMember.getPoint());

    }

    @Test
    @DisplayName("일별 로그인 시 포인트 10 증가")
    void addPoint() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("bbbb","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long addMemberId = memberService.addMember(member);

        em.flush();
        assertEquals(member, memberService.findMember(addMemberId));
        assertEquals(0, member.getPoint());

        LoginLog loginLog = LoginLog.createLoginLog(member.getEmail());
        loginService.addLoginLog(loginLog);
        em.flush();
        Long findLoginLog = loginRepository.findLoginLog(member);
        assertNotEquals(0, findLoginLog);
        loginService.addPoint(member);
        assertEquals(10, member.getPoint(), "일별 1회 로그인 시 포인트 10이 지급되어야 한다.");

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);
        assertEquals(10, member.getPoint(), "일별 1회 로그인 시에만 포인트 10이 지급되어야 한다.");

        em.flush();
        Member changedMember = memberService.findMember(addMemberId);
        assertEquals(10, changedMember.getPoint());

    }
}