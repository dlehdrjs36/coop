package com.projectteam.coop.web.order;

import com.projectteam.coop.domain.*;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.product.ProductForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    private final ProductService productService;
    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @PostMapping("/{id}")
    public String buy(@Login MemberSessionDto loginMember, @PathVariable Long id) {

        Product product = productService.findProduct(id);
        Member member = memberService.findMember(loginMember.getId());
        if (member != null) {
            if (member.buyProduct(product.getPrice())) {
                //구매성공
                purchaseListService.addPurchaseList(product, member);
                memberService.updateMember(member);
                loginMember.setPoint(member.getPoint());
                return "redirect:/";
            }
            //포인트 부족
            return "redirect:/shop";

        }
        //회원 정보 없음
        return "redirect:/login";
    }

    @GetMapping
    public String list(@Login MemberSessionDto loginMember, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(purchaseListService.totalSize());

        List<PurchaseList> findPurchaseList = purchaseListService.findPurchaseList(loginMember, Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("purchaseList", findPurchaseList);

        return "/templates/orders/orderList";
    }

    @PostMapping("/{orderId}/apply")
    @ResponseBody
    public Map<String, Object> orderApply(@PathVariable("orderId") Long orderId, @RequestBody ProductForm productForm) {
        Map<String, Object> result = new HashMap<>();
        result.put("applyAt", "N");
        if (productForm.getType().equals(ProductType.BACKGROUND)) {
            purchaseListService.orderBackgroundApply(orderId);
            result.put("applyAt", "Y");
        } else {
            purchaseListService.orderIconApply(orderId);
            result.put("applyAt", "Y");
        }

        return result;
    }

    @PostMapping("/{orderId}/unapply")
    @ResponseBody
    public Map<String, Object> orderUnApply(@PathVariable("orderId") Long orderId) {
        Map<String, Object> result = new HashMap<>();
        result.put("applyAt", "N");

        purchaseListService.orderUnapply(orderId);
        result.put("applyAt", "Y");

        return result;
    }

}
