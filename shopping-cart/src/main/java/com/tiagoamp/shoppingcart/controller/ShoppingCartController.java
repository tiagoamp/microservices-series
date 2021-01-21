package com.tiagoamp.shoppingcart.controller;

import com.tiagoamp.shoppingcart.domain.Cart;
import com.tiagoamp.shoppingcart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor  @AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService service;

    @PostMapping
    public ResponseEntity<ShoppingCartResponseDTO> submit(@RequestBody ShoppingCartRequestDTO requestDTO) {

        //TODO: convert request cart to model cart
        var cart = new Cart();

        cart = service.purchase(cart);

        //TODO: convert model cart to response cart
        var responseDTO = new ShoppingCartResponseDTO();

        return ResponseEntity.created(URI.create(responseDTO.getId())).body(responseDTO);
    }

}
