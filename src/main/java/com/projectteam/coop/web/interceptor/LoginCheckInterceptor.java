package com.projectteam.coop.web.interceptor;

import com.projectteam.coop.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        log.info("session {}", session);
        //관리자 페이지는 관리자 세션 필요
        if (requestURI.contains("admin")) {
            if (session == null || session.getAttribute(SessionConst.ADMIN_LOGIN_MEMBER) == null) {
                log.info("미인증 사용자 입니다.");
                response.sendRedirect("/");
                return false;
            }
        }else {
            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                log.info("미인증 사용자 입니다.");
                response.sendRedirect("/login?redirectURL=" + requestURI);
                return false;
            }
        }

        return true;
    }
}
