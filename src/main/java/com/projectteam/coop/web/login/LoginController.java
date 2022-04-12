package com.projectteam.coop.web.login;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.web.member.MemberForm;
import com.projectteam.coop.web.post.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.projectteam.coop.web.session.SessionConst.LOGIN_MEMBER;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/templates/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("memberForm") MemberForm memberForm, HttpServletRequest request) {
        //추후 validate로 입력 폼 검증 예정
        if (memberForm != null) {
            Member findMember = memberService.findMember(memberForm.getEmail(), memberForm.getPassword());
            if (findMember != null) {
                HttpSession session = request.getSession();
                session.setAttribute(LOGIN_MEMBER, findMember);
                return "redirect:/";
            }
        }
        return "/templates/login/loginForm";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
