package com.projectteam.coop.service.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.web.member.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Member findMember(String email, String password) {
        return memberRepository.findMember(email, password);
    }
}
