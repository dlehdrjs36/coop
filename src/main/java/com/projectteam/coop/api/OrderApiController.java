package com.projectteam.coop.api;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.ProductType;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.service.purchaselist.PurchaseListService;
import com.projectteam.coop.util.ApiUtil;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.product.ProductForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderApiController {

    private final ProductService productService;
    private final MemberService memberService;
    private final PurchaseListService purchaseListService;

    @PostMapping("/{productId}")
    public ApiResult<OrderApiDto> buy(@Login MemberSessionDto loginMember, @PathVariable Long productId) {
        Member member = memberService.findMember(loginMember.getId());
        if (member != null) {
            Product product = productService.findProduct(productId);
            purchaseListService.addPurchaseList(product, member);
            loginMember.setPoint(member.getPoint());
        }
        return ApiUtil.success(new OrderApiDto("상품을 구매하였습니다.", HttpStatus.OK));
    }

    @PostMapping("/{orderId}/apply")
    public ApiResult<OrderApiDto> orderApply(@Login MemberSessionDto loginMember, @PathVariable Long orderId, @RequestBody ProductForm productForm) {
        if (isBackgroundItem(productForm)) {
            purchaseListService.orderBackgroundApply(loginMember, orderId);
        } else {
            purchaseListService.orderIconApply(loginMember, orderId);
        }
        return ApiUtil.success(new OrderApiDto("상품을 적용하였습니다.", HttpStatus.OK));
    }

    private boolean isBackgroundItem(ProductForm productForm) {
        return productForm.getType().equals(ProductType.BACKGROUND);
    }

    @PostMapping("/{orderId}/unapply")
    public ApiResult<OrderApiDto> orderUnApply(@Login MemberSessionDto loginMember, @PathVariable Long orderId) {
        purchaseListService.orderUnapply(loginMember, orderId);
        return ApiUtil.success(new OrderApiDto("상품을 미적용하였습니다.", HttpStatus.OK));
    }
}
