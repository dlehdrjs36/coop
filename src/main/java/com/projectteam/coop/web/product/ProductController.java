package com.projectteam.coop.web.product;

import com.projectteam.coop.domain.Product;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public String productList(@RequestParam(value = "page", required = false) Integer page, Model model) {
        if (page == null) {
            page = 1;
        }

        Paging paging = new Paging();
        paging.calculateTotalPage(productService.totalSize());

        List<Product> findProduct = productService.findProducts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("products", findProduct);

        return "/templates/products/productList";
    }

    @GetMapping("/products/new")
    public String createForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "/templates/products/createProductForm";
    }

    @PostMapping("/products/new")
    public String create(ProductForm productForm) {
        productService.addProduct(productForm);

        return "redirect:/products";
    }
}
