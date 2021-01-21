package com.tiagoamp.shoppingcart.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor  @NoArgsConstructor
@Getter  @Setter
public class ShoppingCartResponseDTO {

    private String id;
    private Long userId;
    private String userName;
    private List<ItemDTO> items;
    private BigDecimal totalPrice;

}
