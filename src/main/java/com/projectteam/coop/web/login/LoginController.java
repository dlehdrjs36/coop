package com.projectteam.coop.web.login;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.service.login.LoginService;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.web.member.MemberForm;
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
import javax.servlet.http.HttpSession;

import static com.projectteam.coop.web.session.SessionConst.ADMIN_LOGIN_MEMBER;
import static com.projectteam.coop.web.session.SessionConst.LOGIN_MEMBER;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "/templates/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/templates/login/loginForm";
        }

        //이메일과 패스워드가 일치하는 회원 검색
        Member findMember = memberService.findMember(loginForm.getEmail(), loginForm.getPassword());
        if(findMember == null) {
            bindingResult.reject("login");

            return "/templates/login/loginForm";
        } else {
            loginService.addLoginLog(LoginLog.createLoginLog(findMember.getEmail()));
            loginService.addPoint(findMember);//포인트 추가. 로그인 1회시에만 추가되도록 필요
            
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_MEMBER, MemberSessionDto.createSession(findMember));

            return "redirect:/";
        }
    }

    @GetMapping("/adminLogin")
    public String adminLoginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/templates/login/adminLoginForm";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@ModelAttribute("memberForm") MemberForm memberForm, HttpServletRequest request) {
        //추후 validate로 입력 폼 검증 예정
        if (memberForm != null) {
            Member findMember = memberService.findMember(memberForm.getEmail(), memberForm.getPassword());
            if (findMember != null) {
                HttpSession session = request.getSession();
                session.setAttribute(ADMIN_LOGIN_MEMBER, findMember);

                return "/templates/admin/main";
            }
        }
        return "/templates/login/adminLoginForm";
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
