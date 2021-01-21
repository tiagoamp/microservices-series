package com.tiagoamp.productcatalog.service;

import com.tiagoamp.productcatalog.domain.Product;
import com.tiagoamp.productcatalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;
    private ProductMapper mapper;


    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    public void remove(Long id) {
        var product = find(id);
        repository.deleteById(product.getId());
    }

    public Product find(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new NoResultException());
        return mapper.toModel(entity);
    }

    public List<Product> findAll() {
        var entities = repository.findAll();
        return mapper.toModel(entities);
    }

}
