package com.tiagoamp.shoppingcart.controller;

import com.tiagoamp.shoppingcart.domain.Cart;
import com.tiagoamp.shoppingcart.domain.Item;
import com.tiagoamp.shoppingcart.domain.UserInfo;
import com.tiagoamp.shoppingcart.service.CartMapper;
import com.tiagoamp.shoppingcart.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartService service;

    @Spy
    private CartMapper mapper = Mappers.getMapper(CartMapper.class);

    @InjectMocks
    private ShoppingCartController controller;

    private ShoppingCartRequestDTO requestDTO;
    private Cart shoppingCart;


    @BeforeEach
    private void setup() {
        List<ItemDTO> itemsDTOS = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new ItemDTO(Long.valueOf(i), "product name " + i, 2)).collect(toList());
        requestDTO = new ShoppingCartRequestDTO(22L, itemsDTOS);
        var user = new UserInfo(1L, "user name", "email@email.com", "address");
        var items = mapper.toModel(itemsDTOS);
        items.stream().forEach(item -> item.getProduct().setPrice(BigDecimal.TEN));  // set prices
        shoppingCart = new Cart("id", user, items);
    }


    @Test
    void submit() {
        Mockito.when(service.purchase(Mockito.any(Cart.class))).thenReturn(shoppingCart);
        var result = controller.submit(requestDTO);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        assertEquals(shoppingCart.getId(), result.getBody().getId());
        assertTrue(result.getBody().getTotalPrice().compareTo(BigDecimal.ZERO) > 0);
    }

}