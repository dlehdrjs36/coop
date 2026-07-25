package com.projectteam.coop.web.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private  CustomMessageService customMessageService;

    //240707 스프링부트 3.2에서 매개변수의 이름을 인지 못하는 문제 참고 :https://velog.io/@ghwns9991/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-3.2-%EB%A7%A4%EA%B0%9C%EB%B3%80%EC%88%98-%EC%9D%B4%EB%A6%84-%EC%9D%B8%EC%8B%9D-%EB%AC%B8%EC%A0%9C
    @GetMapping("/redirect")
    public String serviceStart(@RequestParam("code") String code) throws JSONException {
        if(authService.getKakaoAuthToken(code)) {
            customMessageService.sendMyMessage();
            return "메시지 전송 성공";
        }else {
            return "토큰발급 실패";
        }
    }
}
