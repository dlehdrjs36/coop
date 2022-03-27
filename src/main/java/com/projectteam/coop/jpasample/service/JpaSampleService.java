package com.projectteam.coop.jpasample.service;

import com.projectteam.coop.jpasample.domain.Member;

import java.util.List;
import java.util.Optional;

public interface JpaSampleService {

    Long addMember(Member member);

    Long deleteMember(Long id);

    Long updateMember(Long id, Member member);

    Optional<Member> findMember(Long id);

    List<Member> findMembers();
}
