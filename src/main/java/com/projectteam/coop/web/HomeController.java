package com.projectteam.coop.web;

import com.projectteam.coop.domain.*;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.post.PostService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.web.argumentresolver.AdminLogin;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @GetMapping("/")
    public String home(@Login MemberSessionDto loginMember, Model model) {
        List<Post> recommendPosts = postService.findRecommendPosts();
        model.addAttribute("recommendPosts", recommendPosts);

        if (loginMember != null) {
            Member member = memberService.findMember(loginMember.getId());
            purchaseListService.memberPurchaseList(member.getEmail())
                    .stream()
                    .filter(purchaseList -> isBackgroundItem(purchaseList))
                    .filter(purchaseList -> isApply(purchaseList))
                    .findAny()
                    .ifPresent(memberPurchaseList -> model.addAttribute("memberPurchaseProduct", memberPurchaseList.getProduct()));
        }
        return "index";
    }

    private boolean isApply(PurchaseList purchaseList) {
        return purchaseList.getStatus() == PurchaseListStatus.APPLY;
    }

    private boolean isBackgroundItem(PurchaseList purchaseList) {
        return purchaseList.getProduct().getType() == ProductType.BACKGROUND;
    }

    //TODO 관리자 페이지 구현 필요
    @GetMapping("/admin")
    public String adminHome(@AdminLogin MemberSessionDto loginMember, Model model) {
        return "admin/main";
    }
}
