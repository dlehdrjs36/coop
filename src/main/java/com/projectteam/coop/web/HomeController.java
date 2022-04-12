package com.projectteam.coop.web;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.web.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "/templates/index";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터 없는 경우
        if (loginMember == null) {
            return "/templates/index";
        }

        //세션에 회원 데이터 있는 경우
        model.addAttribute("member", loginMember);
        return "/templates/loginIndex";
    }
}
