package com.projectteam.coop.sample.mybatis.service;

import com.projectteam.coop.sample.mybatis.repository.SampleMapper;
import com.projectteam.coop.sample.mybatis.domain.SampleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {

    private final SampleMapper sampleDAO;
    //생성자 주입
    public SampleServiceImpl(SampleMapper sampleDAO) {
        this.sampleDAO = sampleDAO;
    }

    @Override
    public List<SampleDTO> findAll() {
        return sampleDAO.findAll();
    }

    @Override
    public Optional<SampleDTO> findById(SampleDTO sampleDTO) {
        return sampleDAO.findById(sampleDTO);
    }

    @Override
    public SampleDTO save(SampleDTO sampleDTO) {
        return sampleDAO.save(sampleDTO);
    }
}
