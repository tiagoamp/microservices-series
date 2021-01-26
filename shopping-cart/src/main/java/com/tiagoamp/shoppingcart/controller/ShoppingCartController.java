package com.tiagoamp.shoppingcart.controller;

import com.tiagoamp.shoppingcart.service.CartMapper;
import com.tiagoamp.shoppingcart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/cart")
@AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService service;
    private CartMapper mapper;

    @PostMapping
    public ResponseEntity<ShoppingCartResponseDTO> submit(@RequestBody ShoppingCartRequestDTO requestDTO) {
        var cart = mapper.toModel(requestDTO);
        cart = service.purchase(cart);
        var responseDTO = mapper.toResponseDTO(cart);
        return ResponseEntity.created(URI.create(responseDTO.getId())).body(responseDTO);
    }

}
