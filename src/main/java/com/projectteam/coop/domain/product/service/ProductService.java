package com.projectteam.coop.domain.product.service;

import com.projectteam.coop.domain.product.model.entity.Product;
import com.projectteam.coop.repository.product.ProductRepository;
import com.projectteam.coop.web.product.ProductForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mysqlTxManager")
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long addProduct(ProductForm productForm) {
        Product product = Product.createProduct(productForm.getName(), productForm.getPath(), productForm.getType(), productForm.getPrice());
        return productRepository.addProduct(product);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public List<Product> findProducts(int offset, int size) {
        return productRepository.findProducts(offset, size);
    }

    @Transactional(transactionManager = "mysqlTxManager", readOnly = true)
    public Product findProduct(Long id) {
        return productRepository.findProduct(id);
    }

    public Long updateProduct(ProductForm productForm) {
        return productRepository.updateProduct(productForm);
    }

    public int totalSize() {
        return productRepository.getTotalSize();
    }

}
