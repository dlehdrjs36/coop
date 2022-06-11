package com.projectteam.coop.web.mail;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final JavaMailSender mailSender;
    private final MemberService memberService;

    @GetMapping(value = "/mail")
    public String getMailAdd(Model model){
        model.addAttribute("emailForm", new EmailForm());
        return "/templates/mail/forgotPassword";
    }

    @PostMapping(value = "/mail")
    public String mailSending(@Validated @ModelAttribute("emailForm") EmailForm emailForm,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/templates/mail/forgotPassword";
        }

        Member findMember = memberService.findMemberForPassword(emailForm.getEmail());
        if(findMember == null){
            bindingResult.reject("memberNotFound");
            return "/templates/mail/forgotPassword";
        }

        String tempPassword = RandomStringUtils.randomAlphanumeric(12);
        changeMemberPasswordToTemporaryPassword(findMember, tempPassword);

        try {
            sendTemporaryPasswordEmailToMember(emailForm.getEmail(), tempPassword);
        } catch (MessagingException e) {
            log.error("error", e);
        }

        return "/templates/mail/mailResultPage";
    }

    private void changeMemberPasswordToTemporaryPassword(Member findMember, String tempPassword) {
        String encryptedTempPassword = SecurityUtil.encryptSHA256(tempPassword, findMember.getSalt());
        findMember.changePassword(encryptedTempPassword);
        memberService.updateMember(findMember);
    }

    private void sendTemporaryPasswordEmailToMember(String memberEmail, String tempPassword) throws MessagingException {
        String setFrom = "TFTInfo";
        String title = "TFTInfo 임시 비밀번호에 대해서"; // 제목
        String content = "임시비밀번호는 아래와 같습니다.\n\n" + tempPassword + "\n\nhttp://tftinfo.tk/login 으로 접속하여 로그인 후 비밀번호를 변경해주시길 바랍니다.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(setFrom); // 보내는 사람 (생략 시 정상 작동 안함)
        messageHelper.setTo(memberEmail); // 받는 사람 이메일
        messageHelper.setSubject(title); // 메일 제목 (생략해도 정상 작동)
        messageHelper.setText(content); // 메일 내용
        mailSender.send(message);
    }
}