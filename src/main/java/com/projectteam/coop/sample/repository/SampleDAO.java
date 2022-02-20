package com.projectteam.coop.sample.repository;

import com.projectteam.coop.sample.domain.SampleDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SampleDAO {

    private final SqlSessionTemplate mybatisSqlSession;
    //생성자 주입
    public SampleDAO(SqlSessionTemplate mybatisSqlSession) {
        this.mybatisSqlSession = mybatisSqlSession;
    }

    public List<SampleDTO> findAll() {
        return mybatisSqlSession.selectList("sample.findAll");
    }

    public Optional<SampleDTO> findById(SampleDTO sampleDTO) {
        List<SampleDTO> result = mybatisSqlSession.selectList("sample.findById", sampleDTO);
        return result.stream().findAny();
    }

    public SampleDTO save(SampleDTO sampleDTO) {
        mybatisSqlSession.insert("sample.save", sampleDTO);
        return sampleDTO;
    }

}
