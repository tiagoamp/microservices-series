package com.tiagoamp.shoppingcart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class ProductoOverview {

    private Long id;
    private String name;
    private BigDecimal price;

}
