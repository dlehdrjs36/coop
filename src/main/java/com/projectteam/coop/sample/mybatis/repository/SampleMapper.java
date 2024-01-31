package com.projectteam.coop.sample.mybatis.repository;

import com.projectteam.coop.sample.mybatis.domain.SampleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface SampleMapper {
    List<SampleDTO> findAll();
    Optional<SampleDTO> findById(SampleDTO sampleDTO);
    SampleDTO save(SampleDTO sampleDTO);
}
