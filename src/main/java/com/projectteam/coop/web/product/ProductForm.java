package com.projectteam.coop.web.product;

import com.projectteam.coop.domain.product.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

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
