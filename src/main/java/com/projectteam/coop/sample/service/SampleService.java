package com.projectteam.coop.sample.service;

import com.projectteam.coop.sample.domain.SampleDTO;

import java.util.List;
import java.util.Optional;

public interface SampleService {
    List<SampleDTO> findAll();

    Optional<SampleDTO> findById(SampleDTO sampleDTO);

    SampleDTO save(SampleDTO sampleDTO);
}
