package com.projectteam.coop.jpasample.service;

import com.projectteam.coop.jpasample.domain.Member;

import java.util.Optional;

public interface JpaSampleService {

    Long join(Member member);

    Optional<Member> findById(Long id);
}
