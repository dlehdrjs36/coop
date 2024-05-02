package com.projectteam.coop.web.login;

import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.domain.login.service.LoginService;
import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.member.service.MemberService;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.argumentresolver.AdminLogin;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.projectteam.coop.web.session.SessionConst.ADMIN_LOGIN_MEMBER;
import static com.projectteam.coop.web.session.SessionConst.LOGIN_MEMBER;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@Login MemberSessionDto loginMember, Model model) {
        if(loginMember == null) {
            model.addAttribute("loginForm", new LoginForm());
            return "login/loginForm";
        }

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Optional<Member> member = memberService.findMemberForPassword(loginForm.getEmail());
        if(member.isEmpty()) {
            bindingResult.reject("memberNotFound");
            return "login/loginForm";
        }
        String salt = member.get().getSalt();

        Member findMember = memberService.findMember(loginForm.getEmail(), SecurityUtil.encryptSHA256(loginForm.getPassword(), salt));
        if(findMember == null) {
            bindingResult.reject("login");
            return "login/loginForm";
        } else {
            addMemberLogAndPoints(findMember);
            addLoginInformationToSession(request, findMember);

            return "redirect:" + redirectURL;
        }
    }

    private void addMemberLogAndPoints(Member findMember) {
        loginService.addLoginLog(LoginLog.createLoginLog(findMember.getEmail()));
        loginService.addPoint(findMember);
    }

    private void addLoginInformationToSession(HttpServletRequest request, Member findMember) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, MemberSessionDto.createSession(findMember));
    }

    @GetMapping("/adminLogin")
    public String adminLoginForm(@AdminLogin MemberSessionDto adminSession, Model model) {
        if(adminSession == null) {
            model.addAttribute("loginForm", new LoginForm());
            return "login/adminLoginForm";
        }
        return "redirect:/admin";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/adminLoginForm";
        }

        Optional<Member> member = memberService.findMemberForPassword(loginForm.getEmail());
        if(member.isEmpty()) {
            bindingResult.reject("memberNotFound");
            return "login/adminLoginForm";
        }
        String salt = member.get().getSalt();

        Member findMember = memberService.findMember(loginForm.getEmail(), SecurityUtil.encryptSHA256(loginForm.getPassword(), salt));
        if(findMember == null) {
            bindingResult.reject("login");
            return "login/adminLoginForm";
        }

        addAdminLoginInformationToSession(request, findMember);

        return "redirect:/admin";
    }

    private void addAdminLoginInformationToSession(HttpServletRequest request, Member findMember) {
        HttpSession session = request.getSession();
        session.setAttribute(ADMIN_LOGIN_MEMBER, MemberSessionDto.createSession(findMember));
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
