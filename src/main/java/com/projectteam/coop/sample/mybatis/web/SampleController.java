package com.projectteam.coop.sample.mybatis.web;

import com.projectteam.coop.sample.mybatis.repository.SampleDAO;
import com.projectteam.coop.sample.mybatis.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class SampleController {

    private final SampleDAO sampleDAO;

    public SampleController(SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }

    @RequestMapping(value = "/mybatisSample", method = RequestMethod.GET)
    public String sample(Model model) {
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setId("testId");

        Optional<SampleDTO> byId = sampleDAO.findById(sampleDTO);
        model.addAttribute("sample", byId.orElse(null));
        //jsp
        return "indexJsp";
    }
}
