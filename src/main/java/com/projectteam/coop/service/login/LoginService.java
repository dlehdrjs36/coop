package com.projectteam.coop.service.login;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.repository.log.LoginRepository;
import com.projectteam.coop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;

    public Long addLoginLog(LoginLog login) {
        return loginRepository.addLoginLog(login);
    }

    public void addPoint(Member member) {
        if (loginRepository.findLoginLog() == 1) {
            memberRepository.addPoint(member);
        }
    }

}
