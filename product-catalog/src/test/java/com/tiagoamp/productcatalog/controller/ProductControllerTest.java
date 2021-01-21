package com.tiagoamp.productcatalog.controller;

import com.tiagoamp.productcatalog.domain.Product;
import com.tiagoamp.productcatalog.service.ProductMapper;
import com.tiagoamp.productcatalog.service.ProductService;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private ProductController controller;

    private ProductRequestDTO requestDTO = new ProductRequestDTO("name", "description", "brand", BigDecimal.TEN);
    private Product product = new Product(1L, "name", "description", "brand", BigDecimal.TEN);


    @Test
    void create() {
        Mockito.when(service.save(Mockito.any(Product.class))).thenReturn(product);
        var result = controller.create(requestDTO);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        assertEquals(product.getId(), result.getBody().getId());
    }

    @Test
    void remove() {
        var result = controller.remove(product.getId());
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCode().value());
        assertNull(result.getBody());
    }

    @Test
    void findById() {
        Mockito.when(service.find(Mockito.anyLong())).thenReturn(product);
        var result = controller.findById(product.getId());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        assertEquals(product.getId(), result.getBody().getId());
    }

    @Test
    void findAll() {
        Mockito.when(service.findAll()).thenReturn(List.of(product));
        var result = controller.findAll();
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        assertEquals(1, result.getBody().size());
    }

}