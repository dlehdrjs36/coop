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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL) {

        if (bindingResult.hasErrors()) {
            return "/templates/login/loginForm";
        }

        //이메일과 패스워드가 일치하는 회원 검색
        Member findMember = memberService.findMember(loginForm.getEmail(), loginForm.getPassword());
        if(findMember == null) {
            bindingResult.reject("login");
            return "/templates/login/loginForm";
        } else {
            //포인트 추가. 일별 로그인 1회만 추가
            loginService.addLoginLog(LoginLog.createLoginLog(findMember.getEmail()));
            loginService.addPoint(findMember);
            //세션 생성
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_MEMBER, MemberSessionDto.createSession(findMember));

            return "redirect:" + redirectURL;
        }
    }

    @GetMapping("/adminLogin")
    public String adminLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "/templates/login/adminLoginForm";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/templates/login/adminLoginForm";
        }
        Member findMember = memberService.findMember(loginForm.getEmail(), loginForm.getPassword());
        if(findMember == null) {
            bindingResult.reject("login");
            return "/templates/login/adminLoginForm";
        }
        //세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(ADMIN_LOGIN_MEMBER, MemberSessionDto.createSession(findMember));

        return "/templates/admin/main";
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
