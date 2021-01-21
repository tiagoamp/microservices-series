package com.tiagoamp.shoppingcart.service;

import com.tiagoamp.shoppingcart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private IntegrationService integrationService;

    public Cart purchase(Cart shoppingCart) {
        var uuid = UUID.randomUUID().toString();
        shoppingCart.setId(uuid);

        //TODO: call remote service for user info

        //TODO: call remote service for products data

        integrationService.submitToBilling(shoppingCart);
        integrationService.notifyToDelivery(shoppingCart);
        integrationService.askForUserReview(shoppingCart);

        return shoppingCart;
    }

}
