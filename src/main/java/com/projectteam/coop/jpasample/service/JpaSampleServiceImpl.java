package com.projectteam.coop.jpasample.service;

import com.projectteam.coop.jpasample.domain.Member;
import com.projectteam.coop.jpasample.repository.JpaSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaSampleServiceImpl implements JpaSampleService {

    private final JpaSampleRepository jpaSampleRepository;

    @Autowired
    public JpaSampleServiceImpl(JpaSampleRepository jpaSampleRepository) {
        this.jpaSampleRepository = jpaSampleRepository;
    }

    @Override
    public Long join(Member member) {
        return jpaSampleRepository.join(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaSampleRepository.findById(id);
    }
}
