package com.projectteam.coop.web.product;

import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.ProductType;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.service.product.ProductService;
import com.projectteam.coop.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;


    @ModelAttribute("productTypes")
    public ProductType[] productType() {
        return ProductType.values();
    }

    @GetMapping
    public String productList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

        Paging paging = new Paging();
        paging.calculateTotalPage(productService.totalSize());

        List<Product> findProduct = productService.findProducts(Paging.calculateStartOffset(page), Paging.calculateLastOffset(page));

        model.addAttribute("paging", paging);
        model.addAttribute("products", findProduct);

        return "/templates/products/productList";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "/templates/products/createProductForm";
    }

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/templates/products/createProductForm";
        }

        productService.addProduct(productForm);
        return "redirect:/admin/products";
    }

    @GetMapping("/{productId}/edit")
    public String updateForm(@PathVariable Long productId, Model model) {
        Product product = productService.findProduct(productId);
        model.addAttribute("productForm", product);
        return "/templates/products/updateProductForm";
    }

    @PostMapping("/{productId}/edit")
    public String update(@PathVariable Long productId, @Validated @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/templates/products/updateProductForm";
        }

        System.out.println("productId = " + productId);
        System.out.println("productId = " + productForm.getId());
        productForm.setId(productId);
        productService.updateProduct(productForm);
        return "redirect:/admin/products";
    }

}
