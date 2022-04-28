package com.projectteam.coop.web.mail;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.web.member.MemberForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final JavaMailSender mailSender;
    private final MemberService memberService;
    @Value("${tomcat.server.port}")
    private String serverPort;

    @GetMapping(value = "/mail")
    public String getMailAdd(Model model){
        model.addAttribute("emailForm", new EmailForm());
        return "/templates/mail/forgotPassword";
    }

    // mailSending 코드
    @PostMapping(value = "/mail")
    public String mailSending(@Validated @ModelAttribute("emailForm") EmailForm emailForm,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/templates/mail/forgotPassword";
        }

        //이메일과 패스워드가 일치하는 회원 검색
        Member findMember = memberService.findMemberForPassword(emailForm.getEmail());

        if(findMember == null){
            bindingResult.reject("memberNotFound");
            return "/templates/mail/forgotPassword";
        }
        String randomPassword = RandomStringUtils.randomAlphanumeric(12);
        MemberForm memberForm = new MemberForm();
        memberForm.setId(findMember.getId());
        memberForm.setName(findMember.getName());
        memberForm.setPassword(randomPassword);
        memberForm.setEmail(findMember.getEmail());
        memberForm.setEmailReceptionType(findMember.getEmailReceptionType());
        memberService.updateMember(memberForm);

        InetAddress local;
        String ip = "";
        try {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        String setfrom = "TFTInfo";
        String tomail = emailForm.getEmail(); // 받는 사람 이메일
        String title = "TFTInfo 임시 비밀번호에 대해서"; // 제목
        String content = "요청하신 임시비밀번호는 아래와 같습니다.\n\n" +
                randomPassword +
                "\n\nhttp://" + ip + ":" + serverPort + "/login 으로 접속해 로그인 한뒤 비밀번호를 반드시 변경해주시길 바랍니다."; // 로컬 호스트 기준

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(tomail); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "/templates/mail/mailResultPage";
    }
}