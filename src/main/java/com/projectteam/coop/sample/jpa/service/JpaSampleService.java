package com.projectteam.coop.sample.jpa.service;

import com.projectteam.coop.sample.jpa.domain.JpaSample;

import java.util.List;
import java.util.Optional;

public interface JpaSampleService {

    Long addMember(JpaSample member);

    Long deleteMember(Long id);

    Long updateMember(Long id, JpaSample member);

    Optional<JpaSample> findMember(Long id);

    List<JpaSample> findMembers();
}
