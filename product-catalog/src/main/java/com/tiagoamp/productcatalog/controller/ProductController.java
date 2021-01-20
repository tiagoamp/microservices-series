package com.tiagoamp.productcatalog.controller;

import com.tiagoamp.productcatalog.service.ProductMapper;
import com.tiagoamp.productcatalog.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor  @AllArgsConstructor
public class ProductController {

    private ProductService service;
    private ProductMapper mapper;


    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO requestDTO) {
        var product = mapper.toModel(requestDTO);
        product = service.save(product);
        var responseDTO = mapper.toResponseDTO(product);
        return ResponseEntity.created(URI.create(responseDTO.getId().toString())).body(responseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody ProductRequestDTO requestDTO) {
        var product = mapper.toModel(requestDTO);
        product.setId(id);
        product = service.save(product);
        var responseDTO = mapper.toResponseDTO(product);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try{
            service.remove(id);
            return ResponseEntity.noContent().build();
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("id") Long id) {
        try{
            var product = service.find(id);
            return ResponseEntity.ok(mapper.toResponseDTO(product));
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        var products = service.findAll();
        var responseDTOs = mapper.toResponseDTO(products);
        return ResponseEntity.ok(responseDTOs);
    }

}
