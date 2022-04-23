package com.projectteam.coop.web.product;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductForm {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String path;

    @NotBlank
    @Range(min = 1, max = 10000)
    private Integer price;
}
