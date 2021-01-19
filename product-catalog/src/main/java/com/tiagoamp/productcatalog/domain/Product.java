package com.tiagoamp.productcatalog.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  @AllArgsConstructor
@Getter  @Setter
public class Product {

    private Long id;
    private String description;
    private String brand;
    private BigDecimal price;

}
