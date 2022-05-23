package com.projectteam.coop.web.order;

import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    private final PurchaseListService purchaseListService;

    @GetMapping
    public String list(@Login MemberSessionDto loginMember, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(purchaseListService.totalSize());

        List<PurchaseList> findPurchaseList = purchaseListService.findPurchaseList(loginMember, Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("purchaseList", findPurchaseList);

        return "/templates/orders/orderList";
    }
}
