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
import java.util.Optional;

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

        Optional<Member> findMember = memberService.findMemberForPassword(emailForm.getEmail());
        if(findMember.isEmpty()){
            bindingResult.reject("memberNotFound");
            return "/templates/mail/forgotPassword";
        }

        String tempPassword = RandomStringUtils.randomAlphanumeric(12);
        changeMemberPasswordToTemporaryPassword(findMember.get(), tempPassword);

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
        String title = "TFTInfo ?????? ??????????????? ?????????"; // ??????
        String content = "????????????????????? ????????? ????????????.\n\n" + tempPassword + "\n\nhttp://tftinfo.tk/login ?????? ???????????? ????????? ??? ??????????????? ?????????????????? ????????????.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(setFrom); // ????????? ?????? (?????? ??? ?????? ?????? ??????)
        messageHelper.setTo(memberEmail); // ?????? ?????? ?????????
        messageHelper.setSubject(title); // ?????? ?????? (???????????? ?????? ??????)
        messageHelper.setText(content); // ?????? ??????
        mailSender.send(message);
    }
}