package com.tiagoamp.productcatalog.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;

}
