package com.projectteam.coop.jpasample.service;

import com.projectteam.coop.jpasample.domain.Member;
import com.projectteam.coop.jpasample.repository.JpaSampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("h2TxManager")
public class JpaSampleServiceImpl implements JpaSampleService {

    private Logger logger = LoggerFactory.getLogger(JpaSampleServiceImpl.class);

    private final JpaSampleRepository jpaSampleRepository;

    @Autowired
    public JpaSampleServiceImpl(JpaSampleRepository jpaSampleRepository) {

        this.jpaSampleRepository = jpaSampleRepository;
    }

    @Override
    public Long addMember(Member member) {
        return jpaSampleRepository.addMember(member);
    }

    @Override
    public Long deleteMember(Long id) {
        return jpaSampleRepository.deleteMember(id);
    }

    @Override
    public Long updateMember(Long id, Member member) {
        return jpaSampleRepository.updateMember(id, member);
    }

    @Override
    @Transactional(value = "h2TxManager", readOnly = true)
    public Optional<Member> findMember(Long id) {
        return jpaSampleRepository.findById(id);
    }

    @Override
    @Transactional(value = "h2TxManager", readOnly = true)
    public List<Member> findMembers() {
        return jpaSampleRepository.findAll();
    }
}
