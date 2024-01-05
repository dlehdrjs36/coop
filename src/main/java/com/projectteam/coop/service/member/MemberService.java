package com.projectteam.coop.service.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.repository.member.MemberRepository;
import com.projectteam.coop.web.member.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    public Long addMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.addMember(member);
    }

    //회원 중복 검사
    public boolean isNotDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        return findMembers.isEmpty();
    }

    //회원 중복 검사
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 수정
    public Long updateMember(MemberForm memberForm) {
        return memberRepository.updateMember(memberForm);
    }

    //회원 수정
    public Long updateMember(Member member) {
        return memberRepository.updateMember(member);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public Member findMember(Long id) {
        return memberRepository.findMember(id);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public Member findMember(String email, String password) {
        return memberRepository.findMember(email, password);
    }

    //이메일 조회( 이메일 단독으로 조회 -> [임시 비밀번호 등록용] )
    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public Optional<Member> findMemberForPassword(String Email) {
        return Optional.ofNullable(memberRepository.findMemberForPassword(Email));
    }
}
