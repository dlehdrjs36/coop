package com.projectteam.coop.web.order;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductService productService;
    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @PostMapping("/orders/{id}")
    public String buy(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        }
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터 없는 경우
        if (loginMember == null) {
            return "redirect:/login";
        }

        Product product = productService.findProduct(id);
        Member member = memberService.findMember(loginMember.getEmail(), loginMember.getPassword());
        if (member != null) {
            if (member.buyProduct(product.getPrice())) {
                //구매성공
                purchaseListService.addPurchaseList(product, member);
                memberService.updateMember(member);
                return "redirect:/";
            }
            //포인트 부족
            return "redirect:/shop";
        }
        return "redirect:/products";
    }

    @GetMapping("/orders")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(purchaseListService.totalSize());

        List<PurchaseList> findPurchaseList = purchaseListService.findPurchaseList(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("purchaseList", findPurchaseList);

        return "/templates/orders/orderList";
    }

    @PostMapping("/orders/{orderId}/apply")
    public String orderApply(@PathVariable("orderId") Long orderId) {
        purchaseListService.orderApply(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{orderId}/unapply")
    public String orderUnApply(@PathVariable("orderId") Long orderId) {
        purchaseListService.orderUnApply(orderId);
        return "redirect:/orders";
    }

}
