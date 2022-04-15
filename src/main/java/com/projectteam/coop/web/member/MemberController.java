package com.projectteam.coop.web.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/templates/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = Member.createMember(memberForm.getEmail(), memberForm.getName(), memberForm.getPassword(), Boolean.TRUE);
        memberService.addMember(member);

        return "redirect:/";
    }
}
