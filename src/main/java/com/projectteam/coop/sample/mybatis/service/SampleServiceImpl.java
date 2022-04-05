package com.projectteam.coop.sample.mybatis.service;

import com.projectteam.coop.sample.mybatis.domain.SampleDTO;
import com.projectteam.coop.sample.mybatis.repository.SampleDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {

    private final SampleDAO sampleDAO;
    //생성자 주입
    public SampleServiceImpl(SampleDAO sampleDAO) {
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
