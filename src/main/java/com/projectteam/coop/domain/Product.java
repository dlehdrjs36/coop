package com.projectteam.coop.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
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

}
