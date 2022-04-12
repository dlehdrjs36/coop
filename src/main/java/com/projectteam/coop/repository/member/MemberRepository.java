package com.projectteam.coop.repository.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.web.member.MemberForm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addMember(Member member) {
        em.persist(member);
        return member.getId();
    }

    //수정
    public Long updateMember(MemberForm memberForm) {
        Member findMember = em.find(Member.class, memberForm.getId());
        findMember.changeMember(memberForm.getName(), memberForm.getPassword(), memberForm.getEmailReceptionType());
        return findMember.getId();
    }

    //단건 조회
    public Member findMember(String email, String password) {

        Member findMember = em.createQuery("SELECT m FROM Member m WHERE m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);

        return findMember;
    }
}
