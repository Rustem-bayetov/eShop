package edu.mum.eshop.domain.product;

import lombok.Data;

@Data
public class ProductFilter {
    private String text;

    private Integer categoryId;
}
