package com.projectteam.coop.service.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.web.member.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long addMember(Member member) {
        return memberRepository.addMember(member);
    }

    public Long updateMember(MemberForm memberForm) {
        return memberRepository.updateMember(memberForm);
    }

    public Long updateMember(Member member) {
        return memberRepository.updateMember(member);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Member findMember(Long id) {
        return memberRepository.findMember(id);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Member findMember(String email, String password) {
        return memberRepository.findMember(email, password);
    }

    //이메일 조회( 이메일 단독으로 조회 -> [임시 비밀번호 등록용] )
    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Member findMemberForPassword(String Email) {
        return memberRepository.findMemberForPassword(Email);
    }
}
