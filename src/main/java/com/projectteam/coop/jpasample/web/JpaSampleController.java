package com.projectteam.coop.jpasample.web;

import com.projectteam.coop.jpasample.domain.Member;
import com.projectteam.coop.jpasample.service.JpaSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class JpaSampleController {

    private final JpaSampleService jpaSampleService;

    @Autowired
    public JpaSampleController(JpaSampleService jpaSampleService) {
        this.jpaSampleService = jpaSampleService;
    }

    //등록
    @GetMapping("/jpaSample")
    @ResponseBody
    public String jpaSample() {
        Member member = new Member();
        member.setId("asdf");
        member.setName("testName");
        member.setAge("23");
        Long createId = jpaSampleService.join(member);

        return "create Id : " + createId;
    }

    //조회
    @GetMapping("/jpaSample2/{id}")
    @ResponseBody
    public Member jpaSample2(@PathVariable Long id) {
        Optional<Member> member = jpaSampleService.findById(id);
        return member.orElse(null);
    }

}
