package com.projectteam.coop.web.shop;

import com.projectteam.coop.domain.Product;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/shop")
    public String shop(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(productService.totalSize());

        List<Product> findProduct = productService.findProducts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("products", findProduct);

        return "/templates/products/shop";
    }

}
