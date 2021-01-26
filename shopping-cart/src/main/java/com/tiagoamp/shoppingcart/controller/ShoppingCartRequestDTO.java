package com.tiagoamp.shoppingcart.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor  @NoArgsConstructor
@Getter  @Setter
public class ShoppingCartRequestDTO {

    private Long userId;
    private List<ItemDTO> items;

}