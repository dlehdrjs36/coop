package com.projectteam.coop.service.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.member.MemberForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional(transactionManager = "mysqlTxManager")
class MemberServiceTest {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입")
    void addMember() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("aaaa","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);

        Long addMemberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(addMemberId);

        assertEquals(member, findMember);
    }

    @Test
    @DisplayName("중복 회원 예외 발생")
    void duplicateMember() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("bbbb","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long addMemberId = memberService.addMember(member);
        em.flush();
        assertEquals(member, memberService.findMember(addMemberId));

        String salt2 = SecurityUtil.getSalt();
        Member duplicateMember = Member.createMember("cccc","test@gmail.com", "test2", SecurityUtil.encryptSHA256("1234", salt2), salt2, Boolean.TRUE);
        em.flush();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.addMember(duplicateMember));
        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());

    }

    @Test
    @DisplayName("중복 회원 예외 미발생")
    void noDuplicateMember() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("dddd","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long addMemberId = memberService.addMember(member);
        em.flush();
        assertEquals(member, memberService.findMember(addMemberId));

        String salt2 = SecurityUtil.getSalt();
        Member duplicateMember = Member.createMember("eeee","test2@gmail.com", "test2", SecurityUtil.encryptSHA256("1234", salt2), salt2, Boolean.TRUE);
        em.flush();

        assertDoesNotThrow(() -> memberService.addMember(duplicateMember));

    }

    @Test
    @DisplayName("회원 수정")
    void updateMember() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("ffff","test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long addMemberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(addMemberId);

        MemberForm memberForm = new MemberForm();
        memberForm.setName(findMember.getName());
        memberForm.setPassword("4444");
        memberForm.setEmailReceptionType(Boolean.FALSE);

        findMember.changeMember(memberForm);
        em.flush();
        Member changedMember = memberService.findMember(addMemberId);

        assertEquals(findMember.getMemberNo(), changedMember.getMemberNo());
        assertEquals(findMember.getEmail(), changedMember.getEmail());
        assertEquals(findMember.getName(), changedMember.getName());
        assertEquals("4444", changedMember.getPassword());
        assertEquals(Boolean.FALSE, changedMember.getEmailReceptionType());

    }

}