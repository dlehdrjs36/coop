package com.projectteam.coop.web.order;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.ProductType;
import com.projectteam.coop.domain.PurchaseList;
import com.projectteam.coop.exception.DuplicatePurchaseProductException;
import com.projectteam.coop.exception.NoMemberSessionException;
import com.projectteam.coop.exception.NoPointException;
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

    @PostMapping("/{productId}")
    @ResponseBody
    public Map<String, Object> buy(@Login MemberSessionDto loginMember, @PathVariable Long productId) {

        Map<String, Object> result = new HashMap<>();

        Product product = productService.findProduct(productId);
        Member member = memberService.findMember(loginMember.getId());

        List<PurchaseList> purchaseLists = purchaseListService.memberPurchaseList(member.getEmail());
        long purchaseCount = purchaseLists.stream()
                .filter(purchaseList -> purchaseList.getProduct().getId() == productId)
                .count();

        if (purchaseCount >= 1) {
            throw new DuplicatePurchaseProductException("이미 구매한 상품입니다.");
        }

        if (member != null) {
            if (member.buyProduct(product.getPrice())) {
                //구매성공
                purchaseListService.addPurchaseList(product, member);
                loginMember.setPoint(member.getPoint());
                result.put("code", "Y");
                result.put("message", "상품을 구매하였습니다.");
                return result;
            }
            //포인트 부족
            throw new NoPointException("포인트가 부족합니다.");
        }
        //회원 세션정보 없음
        throw new NoMemberSessionException("로그인 정보가 없습니다.");
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
    public Map<String, Object> orderApply(@Login MemberSessionDto loginMember, @PathVariable("orderId") Long orderId, @RequestBody ProductForm productForm) {
        Map<String, Object> result = new HashMap<>();
        result.put("applyAt", "N");
        if (productForm.getType().equals(ProductType.BACKGROUND)) {
            purchaseListService.orderBackgroundApply(loginMember, orderId);
            result.put("applyAt", "Y");
        } else {
            purchaseListService.orderIconApply(loginMember, orderId);
            result.put("applyAt", "Y");
        }

        return result;
    }

    @PostMapping("/{orderId}/unapply")
    @ResponseBody
    public Map<String, Object> orderUnApply(@Login MemberSessionDto loginMember, @PathVariable("orderId") Long orderId) {
        Map<String, Object> result = new HashMap<>();
        result.put("applyAt", "N");

        purchaseListService.orderUnapply(loginMember, orderId);
        result.put("applyAt", "Y");

        return result;
    }

}
