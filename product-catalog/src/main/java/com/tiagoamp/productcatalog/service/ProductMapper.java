package com.tiagoamp.productcatalog.service;

import com.tiagoamp.productcatalog.controller.ProductRequestDTO;
import com.tiagoamp.productcatalog.controller.ProductResponseDTO;
import com.tiagoamp.productcatalog.domain.Product;
import com.tiagoamp.productcatalog.repository.ProductEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {

    Product toModel(ProductRequestDTO dto);

    Product toModel(ProductEntity entity);

    List<Product> toModel(List<ProductEntity> entities);

    ProductEntity toEntity(Product model);

    ProductResponseDTO toResponseDTO(Product model);

    List<ProductResponseDTO> toResponseDTO(List<Product> models);

}
