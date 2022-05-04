package com.projectteam.coop.repository.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.web.member.MemberForm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addMember(Member member) {
        em.persist(member);
        return member.getId();
    }

    //수정(폼)
    public Long updateMember(MemberForm memberForm) {
        Member findMember = em.find(Member.class, memberForm.getId());
        findMember.changeMember(memberForm);
        return findMember.getId();
    }

    //수정
    public Long updateMember(Member member) {
        Member findMember = em.find(Member.class, member.getId());
        findMember.changeMember(member);
        return member.getId();
    }

    //단건 조회
    public Member findMember(Long id) {

        Member findMember = em.createQuery("SELECT m FROM Member m left join fetch m.purchaseLists WHERE m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return findMember;
    }

    //단건 조회(로그인)
    public Member findMember(String email, String password) {

        Member findMember = em.createQuery("SELECT m FROM Member m left join fetch m.purchaseLists WHERE m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findAny()
                .orElseGet(() -> null);

        return findMember;
    }

    //중복 회원 목록 조회
    public List<Member> findByEmail(String email) {
        List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();

        return memberList;
    }

    //이메일 조회( 이메일 단독으로 조회 -> [임시 비밀번호 등록용] )
    public Member findMemberForPassword(String email) {
        Member member = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
        return member;
    }

    //등록
    public void addPoint(Member member) {
        member.addPoint();
        em.merge(member);
    }
}
