package com.tiagoamp.shoppingcart.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class Cart {

    private String id;
    private UserInfo user;
    private List<Item> items;
    private BigDecimal totalPrice;


    public Cart(String id, UserInfo user, List<Item> items) {
        this(id, user, items, BigDecimal.ZERO);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        if (items == null) return;
        totalPrice = items.stream()
            .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

}
