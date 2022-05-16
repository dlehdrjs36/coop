package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    private String path;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private String createMemberId;

    private String updateMemberId;

    //상품 등록
    public static Product createProduct(String name, String path, ProductType type, Integer price) {
        Product product = new Product();
        product.name = name;
        product.path = path;
        product.type = type;
        product.price = price;
        product.status = ProductStatus.Y;

        return product;
    }

    //상품 수정
    public void changeProduct(String name, String path, ProductType type, Integer price) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.price = price;
    }
}
