package com.projectteam.coop.service.product;

import com.projectteam.coop.domain.product.enums.ProductType;
import com.projectteam.coop.domain.product.model.entity.Product;
import com.projectteam.coop.domain.product.service.ProductService;
import com.projectteam.coop.web.product.ProductForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("local")
@SpringBootTest(
        properties =
                "spring.config.import="
                        + "optional:../properties/local_security/coop_server_local.yml,"
                        + "optional:../properties/coop_server.yml",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(transactionManager = "mysqlTxManager")
class ProductServiceTest {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    @Autowired
    private ProductService productService;


    @Test
    @DisplayName("상품 등록")
    void addProduct() {
        ProductForm productForm = new ProductForm();
        productForm.setName("test");
        productForm.setPath("background03.jpg");
        productForm.setType(ProductType.BACKGROUND);
        productForm.setPrice(5);

        Long productId = productService.addProduct(productForm);
        em.flush();
        em.clear();

        Product product = em.find(Product.class, productId);
        Assertions.assertEquals(productForm.getName(), product.getName());
        Assertions.assertEquals(productForm.getPath(), product.getPath());
        Assertions.assertEquals(productForm.getType(), product.getType());
        Assertions.assertEquals(productForm.getPrice(), product.getPrice());
    }

    @Test
    @DisplayName("상품 수정")
    void updatePost() {
        ProductForm productForm = new ProductForm();
        productForm.setName("test");
        productForm.setPath("background03.jpg");
        productForm.setType(ProductType.BACKGROUND);
        productForm.setPrice(5);

        Product product = em.find(Product.class, productService.addProduct(productForm));
        product.changeProduct(product.getName(), product.getPath(), ProductType.ICON, 10);
        Long productId = product.getId();
        em.flush();
        em.clear();
        Product changeProduct = em.find(Product.class, productId);

        Assertions.assertEquals(productForm.getName(), changeProduct.getName());
        Assertions.assertEquals(productForm.getPath(), changeProduct.getPath());
        Assertions.assertEquals(ProductType.ICON, changeProduct.getType());
        Assertions.assertEquals(10, changeProduct.getPrice());
    }

}