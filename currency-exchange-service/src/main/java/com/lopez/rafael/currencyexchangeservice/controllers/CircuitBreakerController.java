package com.lopez.rafael.currencyexchangeservice.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    // Resilience4j's default retry strategy is to try 3 times if there's an error (exception).
    // Only after the third failure, it will return the actual error
    // @Retry(name = "default")
    // @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    // Look at the diagram https://resilience4j.readme.io/docs/circuitbreaker#introduction to understand Circuit Breaker
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Sample API call received");

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/some-dummy-call",
                String.class
        );

        return forEntity.getBody();
    }

    @GetMapping("/sample-api-rate-limiter")
    // Use RateLimiter when you want to allow only x number of calls over a y period of time
    // Look at application.properties for the custom configuration we added for the default instance
    @RateLimiter(name="default")
    public String sampleApiRateLimiter() {
        logger.info("Sample API call received");
        return "sample-api";
    }

    @GetMapping("/sample-api-bulkhead")
    // Use Bulkhead to limit the amount of concurrent calls
    // Look at application.properties for the sample-api configuration
    @Bulkhead(name="sample-api")
    public String sampleApiBulkhead() {
        logger.info("Sample API call received");
        return "sample-api";
    }

    // You can have different fallback methods based on the exception
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
