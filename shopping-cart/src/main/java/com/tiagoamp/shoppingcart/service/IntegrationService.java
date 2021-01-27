package com.tiagoamp.shoppingcart.service;

import com.tiagoamp.shoppingcart.domain.Cart;
import com.tiagoamp.shoppingcart.domain.Item;
import com.tiagoamp.shoppingcart.domain.ProductOverview;
import com.tiagoamp.shoppingcart.domain.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Provide remote and mock implementations to others integration services,
 * like billing, delivery and review services.
 */
@Service
@AllArgsConstructor
public class IntegrationService {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    // private final String USER_BASE_URL = "http://localhost:8082/api/user/";
    // private final String PRODUCT_BASE_URL = "http://localhost:8081/api/product/";
    private final String USER_SERVICE_NAME = "USER-INFORMATION-SERVICE";
    private final String PRODUCT_SERVICE_NAME = "PRODUCT-CATALOG-SERVICE";


    public UserInfo getRemoteUserInfo(Long userId) {

        // IMPLEMENTATION 01: using RestTemplate
        // var user = fetchDataWithRestTemplate("http://" + USER_SERVICE_NAME + "/api/user/", userId, UserInfo.class);

        // IMPLEMENTATION 02: using WebClient
        // var user = fetchDataWithWebClient("http://" + USER_SERVICE_NAME + "/api/user/", userId, UserInfo.class);

        // IMPLEMENTATION 03: using Feign
        var user = userFeignClient.findById(userId);

        return user;
    }

    public List<Item> getRemoteProductItemsInfo(List<Item> items) {
        items.forEach(item -> {

            // IMPLEMENTATION 01: using RestTemplate
            // var product = fetchDataWithRestTemplate("http://" + PRODUCT_SERVICE_NAME + "/api/product", item.getProduct().getId(), ProductOverview.class);

            // IMPLEMENTATION 02: using WebClient
            // var product = fetchDataWithWebClient("http://" + PRODUCT_SERVICE_NAME  + "/api/product", item.getProduct().getId(), ProductOverview.class);

            // IMPLEMENTATION 03: using Feign
            var product = productFeignClient.findById(item.getProduct().getId());

            item.setProduct(product);
        });

        return items;
    }

    public void submitToBilling(Cart shoppingCart) {
        // Pretend to submit to Billing Service
    }

    public void notifyToDelivery(Cart shoppingCart) {
        // Pretend to submit to Delivery Service
    }

    public void askForUserReview(Cart shoppingCart) {
        // Pretend to submit to Review Service
    }


    // Generic implementation for Rest Template call
    private <T> T fetchDataWithRestTemplate(String url, Long id, Class<T> clazz) {
        return restTemplate.getForObject(url + id, clazz);
    }

    // Generic implementation for WebClient
    private <T> T fetchDataWithWebClient(String url, Long id, Class<T> clazz) {
        return webClientBuilder.build().get()
                .uri(url + id).retrieve()
                .bodyToMono(clazz)
                .block(); // makes sync
    }

    @Deprecated   // replaced by generic method
    private UserInfo fetchUserWithRestTemplate(Long userId) {
        return restTemplate.getForObject("http://" + USER_SERVICE_NAME + "/api/user/" + userId, UserInfo.class);
    }

    @Deprecated   // replaced by generic method
    private ProductOverview fetchProductWithRestTemplate(Long productId) {
        return restTemplate.getForObject("http://" + PRODUCT_SERVICE_NAME + "/api/product/" + productId, ProductOverview.class);
    }

}
