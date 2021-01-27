package com.tiagoamp.shoppingcart.service;

import com.tiagoamp.shoppingcart.domain.ProductOverview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8081/api/product/", name = "ProductCatalogService")
@FeignClient("PRODUCT-CATALOG-SERVICE")
public interface ProductFeignClient {

    @GetMapping("/api/product/{id}")
    ProductOverview findById(@PathVariable("id") Long id);

}
