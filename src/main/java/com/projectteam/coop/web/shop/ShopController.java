package com.projectteam.coop.web.shop;

import com.projectteam.coop.domain.product.model.entity.Product;
import com.projectteam.coop.domain.purchaselist.model.entity.PurchaseList;
import com.projectteam.coop.domain.product.service.ProductService;
import com.projectteam.coop.domain.purchaselist.service.PurchaseListService;
import com.projectteam.coop.util.Paging;
import com.projectteam.coop.web.argumentresolver.Login;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final PurchaseListService purchaseListService;

    @GetMapping("/shop")
    public String shop(@Login MemberSessionDto loginMember, @RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(productService.totalSize());

        List<Product> findProducts = productService.findProducts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));
        if(loginMember != null) {
            List<PurchaseList> purchaseLists = purchaseListService.memberPurchaseList(loginMember.getEmail());
            Map<String, Object> purchaseProducts = purchaseLists.stream()
                    .collect(Collectors.toMap(
                            purchaseList -> String.valueOf(purchaseList.getProduct().getId()),
                            purchaseList -> purchaseList.getProduct(),
                            (oldValue, newValue) -> oldValue)
                    );
            model.addAttribute("purchaseProducts", purchaseProducts);
        }

        model.addAttribute("paging", paging);
        model.addAttribute("products", findProducts);

        return "products/shop";
    }

}
