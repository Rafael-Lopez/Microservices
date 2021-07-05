package com.lopez.rafael.currencyexchangeservice.controllers;

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
    @Retry(name = "sample-api")
    public String sampleApi() {
        logger.info("Sample API call received");

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/some-dummy-call",
                String.class
        );

        return forEntity.getBody();
    }
}
