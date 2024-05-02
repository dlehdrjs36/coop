package com.projectteam.coop.repository.product;

import com.projectteam.coop.domain.product.model.entity.Product;
import com.projectteam.coop.web.product.ProductForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    //등록
    public Long addProduct(Product product) {
        em.persist(product);
        return product.getId();
    }

    //삭제
    public void removeProduct(Long id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }

    //수정
    public Long updateProduct(ProductForm productForm) {
        Product findProduct = em.find(Product.class, productForm.getId());
        findProduct.changeProduct(productForm.getName(), productForm.getPath(), productForm.getType(), productForm.getPrice());
        return findProduct.getId();
    }

    //목록 조회
    public List<Product> findProducts(int offset, int size) {
        List<Product> findProducts = em.createQuery("select p from Product p", Product.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        return findProducts;
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from Product p", Product.class)
                .getResultList()
                .size();

        return size;
    }

    //단건 조회
    public Product findProduct(Long id) {
        Product product = em.find(Product.class, id);
        return product;
    }
}
