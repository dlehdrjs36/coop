package com.projectteam.coop.web.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String create(@ModelAttribute(name = "memberForm") MemberForm memberForm, BindingResult bindingResult) {

        //검증 로직
        if (!StringUtils.hasText(memberForm.getEmail())) {
            bindingResult.addError(new FieldError("memberForm", "email", memberForm.getEmail(), false, new String[]{"required.member.email"}, null, null));
        }
        if (!StringUtils.hasText(memberForm.getPassword())) {
            bindingResult.addError(new FieldError("memberForm", "password", memberForm.getPassword(), false, new String[]{"required.member.password"}, null, null));
        }
        if (!StringUtils.hasText(memberForm.getName())) {
            bindingResult.addError(new FieldError("memberForm", "name", memberForm.getName(), false, new String[]{"required.member.name"}, null, null));
        }

        if (bindingResult.hasErrors()) {
            return "/templates/members/createMemberForm";
        }

        Member member = Member.createMember(memberForm.getEmail(), memberForm.getName(), memberForm.getPassword(), Boolean.TRUE);
        memberService.addMember(member);

        return "redirect:/";
    }
}
