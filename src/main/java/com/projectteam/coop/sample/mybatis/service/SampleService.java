package com.projectteam.coop.sample.mybatis.service;

import com.projectteam.coop.sample.mybatis.domain.SampleDTO;

import java.util.List;
import java.util.Optional;

public interface SampleService {
    List<SampleDTO> findAll();

    Optional<SampleDTO> findById(SampleDTO sampleDTO);

    SampleDTO save(SampleDTO sampleDTO);
}
