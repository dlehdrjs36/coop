package com.projectteam.coop.web.product;

import com.projectteam.coop.domain.ProductType;
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
    private ProductType type;

    @NotBlank
    @Range(min = 1, max = 10000)
    private Integer price;
}
