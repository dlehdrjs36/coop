package com.projectteam.coop.sample.jpa.web;

import com.projectteam.coop.sample.jpa.domain.JpaSample;
import com.projectteam.coop.sample.jpa.service.JpaSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JpaSampleController {

    private final JpaSampleService jpaSampleService;

    //등록
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/jpaSample")
    @ResponseBody
    public String jpaSample() {
        JpaSample member = new JpaSample();
        member.setId("asdf");
        member.setName("testName");
        member.setAge("23");
        Long createId = jpaSampleService.addMember(member);
        return "create Id : " + createId;
    }

    //수정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/jpaSample/{id}")
    @ResponseBody
    public String jpaSample(@PathVariable Long id, JpaSample member) {
        Long updateId = jpaSampleService.updateMember(id, member);
        return "update Id : " + updateId;
    }

    //삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/jpaSample/{id}")
    @ResponseBody
    public String jpaSample(@PathVariable Long id) {
        Long deleteId = jpaSampleService.deleteMember(id);
        return "delete Id : " + deleteId;
    }

    //조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jpaSample/{id}")
    @ResponseBody
    public JpaSample jpaSample2(@PathVariable Long id) {
        Optional<JpaSample> member = jpaSampleService.findMember(id);
        return member.orElse(null);
    }

    //목록 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jpaSample")
    @ResponseBody
    public List<JpaSample> jpaSampleList() {
        List<JpaSample> members = jpaSampleService.findMembers();
        return members;
    }
}
