package com.projectteam.coop.sample.jpa.service;

import com.projectteam.coop.sample.jpa.domain.JpaSample;
import com.projectteam.coop.sample.jpa.repository.JpaSampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("mysqlTxManager")
public class JpaSampleServiceImpl implements JpaSampleService {

    private Logger logger = LoggerFactory.getLogger(JpaSampleServiceImpl.class);

    private final JpaSampleRepository jpaSampleRepository;

    @Autowired
    public JpaSampleServiceImpl(JpaSampleRepository jpaSampleRepository) {

        this.jpaSampleRepository = jpaSampleRepository;
    }

    @Override
    public Long addMember(JpaSample member) {
        return jpaSampleRepository.addMember(member);
    }

    @Override
    public Long deleteMember(Long id) {
        return jpaSampleRepository.deleteMember(id);
    }

    @Override
    public Long updateMember(Long id, JpaSample member) {
        return jpaSampleRepository.updateMember(id, member);
    }

    @Override
    @Transactional(value = "mysqlTxManager", readOnly = true)
    public Optional<JpaSample> findMember(Long id) {
        return jpaSampleRepository.findById(id);
    }

    @Override
    @Transactional(value = "mysqlTxManager", readOnly = true)
    public List<JpaSample> findMembers() {
        return jpaSampleRepository.findAll();
    }
}
