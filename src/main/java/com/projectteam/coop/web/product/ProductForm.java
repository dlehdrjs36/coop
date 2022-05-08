package com.projectteam.coop.web.product;

import com.projectteam.coop.domain.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductForm {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String path;

    @NotNull
    private ProductType type;

    @NotNull
    @Range(min = 1, max = 10000)
    private Integer price;
}
