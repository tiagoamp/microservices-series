package com.tiagoamp.productcatalog.repository;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

}
