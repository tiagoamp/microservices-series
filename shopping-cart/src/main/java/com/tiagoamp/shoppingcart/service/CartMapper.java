package com.tiagoamp.shoppingcart.service;

import com.tiagoamp.shoppingcart.controller.ItemDTO;
import com.tiagoamp.shoppingcart.controller.ShoppingCartRequestDTO;
import com.tiagoamp.shoppingcart.controller.ShoppingCartResponseDTO;
import com.tiagoamp.shoppingcart.domain.Cart;
import com.tiagoamp.shoppingcart.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface CartMapper {

    @Mapping(source = "userId", target = "user.id")
    Cart toModel(ShoppingCartRequestDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    ShoppingCartResponseDTO toResponseDTO(Cart model);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productName", target = "product.name")
    Item toModel(ItemDTO dto);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productName", target = "product.name")
    List<Item> toModel(List<ItemDTO> dto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ItemDTO toDTO(Item model);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    List<ItemDTO> toDTO(List<Item> model);

}
