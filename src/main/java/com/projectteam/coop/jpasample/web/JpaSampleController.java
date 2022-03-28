package com.projectteam.coop.jpasample.web;

import com.projectteam.coop.jpasample.domain.Member;
import com.projectteam.coop.jpasample.service.JpaSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        Member member = new Member();
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
    public String jpaSample(@PathVariable Long id, Member member) {
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
    public Member jpaSample2(@PathVariable Long id) {
        Optional<Member> member = jpaSampleService.findMember(id);
        return member.orElse(null);
    }

    //목록 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jpaSample")
    @ResponseBody
    public List<Member> jpaSampleList() {
        List<Member> members = jpaSampleService.findMembers();
        return members;
    }
}
