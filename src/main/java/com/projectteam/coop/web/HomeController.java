package com.projectteam.coop.web;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.ProductType;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.domain.PurchaseListStatus;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.web.argumentresolver.AdminLogin;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@Login MemberSessionDto loginMember, Model model) {

        //세션에 회원 데이터 있는 경우
        if (loginMember != null) {
            Member member = memberService.findMember(loginMember.getId());
            PurchaseList memberPurchaseList = purchaseListService.memberPurchaseList(member.getEmail())
                    .stream()
                    .filter(purchaseList -> purchaseList.getProduct().getType() == ProductType.BACKGROUND)
                    .filter(purchaseList -> purchaseList.getStatus() == PurchaseListStatus.APPLY)
                    .findAny()
                    .orElseGet(() -> null);

            if (memberPurchaseList != null) {
                model.addAttribute("memberPurchaseProduct", memberPurchaseList.getProduct());
            }
        }

        return "/templates/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome(@AdminLogin MemberSessionDto loginMember, Model model) {
        return "/templates/admin/main";
    }
}
