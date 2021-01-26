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

    private RestTemplate restRemplate;
    private WebClient.Builder webClientBuilder;

    private final String USER_BASE_URL = "http://localhost:8082/api/user/";
    private final String PRODUCT_BASE_URL = "http://localhost:8081/api/product/";


    public UserInfo getRemoteUserInfo(Long userId) {

        // IMPLEMENTATION 01: using RestTemplate
        // var user = fetchDataWithRestTemplate(USER_BASE_URL, userId, UserInfo.class);

        // IMPLEMENTATION 02: using WebClient
        var user = fetchDataWithWebClient(USER_BASE_URL, userId, UserInfo.class);

        return user;
    }

    public List<Item> getRemoteProductItemsInfo(List<Item> items) {
        items.forEach(item -> {

            // IMPLEMENTATION 01: using RestTemplate
            // var product = fetchDataWithRestTemplate(PRODUCT_BASE_URL, item.getProduct().getId(), ProductOverview.class);

            // IMPLEMENTATION 02: using WebClient
            var product = fetchDataWithWebClient(PRODUCT_BASE_URL, item.getProduct().getId(), ProductOverview.class);

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
        return restRemplate.getForObject(url + id, clazz);
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
        return restRemplate.getForObject(USER_BASE_URL + userId, UserInfo.class);
    }

    @Deprecated   // replaced by generic method
    private ProductOverview fetchProductWithRestTemplate(Long productId) {
        return restRemplate.getForObject(PRODUCT_BASE_URL + productId, ProductOverview.class);
    }

}
