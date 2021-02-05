package com.tiagoamp.shoppingcart;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWeClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(4))
				.build();
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(1000))
				.slidingWindowSize(2)
				.build();
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig)
				.circuitBreakerConfig(circuitBreakerConfig)
				.build());
	}

}
