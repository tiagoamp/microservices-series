package com.tiagoamp.shoppingcart.domain;

import lombok.*;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class Item {

    private ProductoOverview product;
    private int quantity;

}
