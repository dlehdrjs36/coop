package com.projectteam.coop.service.product;

import com.projectteam.coop.domain.Product;
import com.projectteam.coop.repository.product.ProductRepository;
import com.projectteam.coop.web.product.ProductForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long addProduct(ProductForm productForm) {
        Product product = Product.createProduct(productForm.getName(), productForm.getPath(), productForm.getPrice());
        return productRepository.addProduct(product);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Product> findProducts(int offset, int size) {
        return productRepository.findProducts(offset, size);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Product findProduct(Long id) {
        return productRepository.findProduct(id);
    }

    public Long updatePost(ProductForm productForm) {
        return productRepository.updateProduct(productForm);
    }

    public int totalSize() {
        return productRepository.getTotalSize();
    }

}
