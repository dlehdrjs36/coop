package com.projectteam.coop.web;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.ProductType;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.domain.PurchaseListStatus;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model) {

        //세션에 회원 데이터 없는 경우
        if (loginMember == null) {
            return "/templates/index";
        }

        //세션에 회원 데이터 있는 경우
        Member member = memberService.findMember(loginMember.getEmail(), loginMember.getPassword());

        PurchaseList memberPurchaseList = purchaseListService.memberPurchaseList(member.getEmail())
                .stream()
                .filter(purchaseList -> purchaseList.getProduct().getType() == ProductType.BACKGROUND)
                .filter(purchaseList -> purchaseList.getStatus() == PurchaseListStatus.APPLY)
                .findAny()
                .orElse(null);

        if (memberPurchaseList != null) {
            model.addAttribute("memberPurchaseProduct", memberPurchaseList.getProduct());
        }

        model.addAttribute("member", member);

        return "/templates/loginIndex";
    }
}
