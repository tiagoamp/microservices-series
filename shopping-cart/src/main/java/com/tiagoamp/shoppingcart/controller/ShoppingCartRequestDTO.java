package com.tiagoamp.shoppingcart.controller;

import com.tiagoamp.shoppingcart.domain.Cart;
import com.tiagoamp.shoppingcart.domain.UserInfo;
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
