package com.projectteam.coop.web.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

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

    @GetMapping("/members/update")
    public String editProfile(@Login MemberSessionDto loginMember, Model model) {

        //세션에 회원 데이터 없는 경우
        if (loginMember == null) {
            return "redirect:/login";
        }

        Member member = memberService.findMember(loginMember.getId());
        if (member != null) {
            MemberForm memberForm = new MemberForm();
            memberForm.setId(member.getId());
            memberForm.setName(member.getName());
            memberForm.setEmail(member.getEmail());
            memberForm.setEmailReceptionType(member.getEmailReceptionType());


            model.addAttribute("memberForm", memberForm);
            return "/templates/members/updateMemberForm";
        }
        //회원 정보 없음
        return "redirect:/";
    }

    @PostMapping("/members/update")
    public String editPassword(@Login MemberSessionDto loginMember, HttpServletRequest httpRequest, Model model){
        String currentPassword = httpRequest.getParameter("currentPassword");
        String changePassword = httpRequest.getParameter("password");

        //이메일과 패스워드가 일치하는 회원 검색
        Member findMember = memberService.findMember(loginMember.getEmail(), currentPassword);
        Member member = memberService.findMember(loginMember.getId());
        MemberForm memberForm = new MemberForm();
        memberForm.setId(member.getId());
        memberForm.setName(member.getName());
        memberForm.setEmail(member.getEmail());
        memberForm.setEmailReceptionType(member.getEmailReceptionType());

        if(findMember == null) {
            if (loginMember != null) {
                model.addAttribute("memberForm", memberForm);
                model.addAttribute("errorMessage","입력한 현재 비밀번호가 일치하지 않습니다.");
                return "/templates/members/updateMemberForm";
            }
            //회원 정보 없음
            return "redirect:/";
        }

        memberForm.setPassword(changePassword);
        memberService.updateMember(memberForm);
        return "redirect:/";
    }
}
