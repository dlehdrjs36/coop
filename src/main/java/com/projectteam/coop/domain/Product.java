package com.projectteam.coop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product", catalog = "coop")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ProductType type;

    @Column(name = "PRICE")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ProductStatus status;

    @Column(name = "CREATE_MEMBER_ID")
    private String createMemberId;

    @Column(name = "UPDATE_MEMBER_ID")
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
