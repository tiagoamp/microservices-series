package com.tiagoamp.productcatalog.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor  @NoArgsConstructor
@Getter  @Setter
public class ProductResponseDTO {

    private Long id;
    private String description;
    private String brand;
    private BigDecimal price;

}
