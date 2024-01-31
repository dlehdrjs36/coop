package com.projectteam.coop.web.member;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Validated @ModelAttribute(name = "memberForm") MemberInsertForm memberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }

        String salt = SecurityUtil.getSalt();
        String password = SecurityUtil.encryptSHA256(memberForm.getPassword(), salt);
        String memberId = memberForm.getEmail() + UUID.randomUUID();
        Member member = Member.createMember(memberId, memberForm.getEmail(), memberForm.getName(), password, salt, Boolean.TRUE);
        if(memberService.isNotDuplicateMember(member)) {
            memberService.addMember(member);
            return "redirect:/";
        }

        bindingResult.rejectValue("email", "memberDuplicate");
        return "members/createMemberForm";
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
            memberForm.setMemberId(member.getMemberId());
            memberForm.setName(member.getName());
            memberForm.setEmail(member.getEmail());
            memberForm.setEmailReceptionType(member.getEmailReceptionType());

            model.addAttribute("memberForm", memberForm);
            return "members/updateMemberForm";
        }
        //회원 정보 없음
        return "redirect:/";
    }

    @PostMapping("/members/update")
    public String editPassword(@Login MemberSessionDto loginMember, HttpServletRequest httpRequest, Model model){
        String oldPassword = httpRequest.getParameter("currentPassword");
        String newPassword = httpRequest.getParameter("password");

        Member member = memberService.findMember(loginMember.getId());
        if(member == null) {
            return "redirect:/";
        }

        String encryptedOldPassword = SecurityUtil.encryptSHA256(oldPassword, member.getSalt());
        if(isNotMatchedPassword(encryptedOldPassword, member.getPassword())) {
            model.addAttribute("memberForm", loginMember);
            model.addAttribute("errorMessage","입력한 현재 비밀번호가 일치하지 않습니다.");
            return "members/updateMemberForm";
        }

        String encryptedNewPassword = SecurityUtil.encryptSHA256(newPassword, member.getSalt());
        member.changePassword(encryptedNewPassword);
        memberService.updateMember(member);

        return "redirect:/";
    }

    private boolean isNotMatchedPassword(String encryptedCurrentPassword, String memberPassword) {
        return !encryptedCurrentPassword.equals(memberPassword);
    }
}
