package com.projectteam.coop.service.login;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Long addPoint(Member member) {
        return memberRepository.addPoint(member);
    }
}
