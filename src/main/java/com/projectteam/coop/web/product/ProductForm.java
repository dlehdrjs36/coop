package com.projectteam.coop.web.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
    private Long id;
    private String name;
    private String path;
    private Integer price;
}
