package com.tiagoamp.productcatalog.service;

import com.tiagoamp.productcatalog.domain.Product;
import com.tiagoamp.productcatalog.repository.ProductEntity;
import com.tiagoamp.productcatalog.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    @Spy  // injects this specific instance to target class
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private ProductService service;

    private ProductEntity productEntity = new ProductEntity(1L, "name", "Description", "Brand", BigDecimal.TEN);


    @DisplayName("Should create product")
    @Test
    void save() {
        Mockito.when(repo.save(Mockito.any(ProductEntity.class))).thenReturn(productEntity);
        var result = service.save(new Product());
        assertTrue(result.getId() != null);
    }

    @DisplayName("Should remove product")
    @Test
    void remove() {
        try {
            Mockito.when(repo.findById(productEntity.getId())).thenReturn(Optional.of(productEntity));
            service.remove(productEntity.getId());
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @DisplayName("Should throw exception when product id does not exist")
    @Test
    void remove_throwException() {
        Mockito.when(repo.findById(Mockito.anyLong())).thenThrow(NoResultException.class);
        assertThrows(NoResultException.class, () -> service.remove(1L));
    }

    @DisplayName("Should return product by id when it exists")
    @Test
    void find() {
        Mockito.when(repo.findById(productEntity.getId())).thenReturn(Optional.of(productEntity));
        var result = service.find(productEntity.getId());
        assertEquals(result.getId(), productEntity.getId());
    }

    @DisplayName("Should throw exception when id does not exists")
    @Test
    void find_throwException() {
        Mockito.when(repo.findById(Mockito.anyLong())).thenThrow(NoResultException.class);
        assertThrows(NoResultException.class, () -> service.find(1L));
    }

    @DisplayName("Should return product list")
    @Test
    void findAll() {
        var entitiesList = List.of(new ProductEntity(1L, "name", "Description", "Brand", BigDecimal.TEN),
                new ProductEntity(2L, "name", "Description 2", "Brand 2", BigDecimal.TEN));
        Mockito.when(repo.findAll()).thenReturn(entitiesList);
        var result = service.findAll();
        assertEquals(entitiesList.size(), result.size());
    }

    @DisplayName("Should return empty list when does not have registered products")
    @Test
    void findAll_throwsException() {
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
        var result = service.findAll();
        assertTrue(result.isEmpty());
    }

}