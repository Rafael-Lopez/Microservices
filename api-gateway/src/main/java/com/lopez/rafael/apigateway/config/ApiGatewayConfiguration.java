package com.lopez.rafael.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // When request sent to /get, we want to re-direct
                .route(p -> p.path("/get")
                        .filters(f -> f
                                // Add a header to the request before re-directing
                                .addRequestHeader("MyHeader", "MyURI")
                                // Add a parameter to the request before re-directing
                                .addRequestParameter("Param", "Value"))
                        // Destination
                        .uri("http://httpbin.org:80"))
                // When request starts with currency-exchange, we want to redirect it using the naming server
                .route(p -> p.path("/currency-exchange/**")
                        // To continue using load balancing, we can simply use:
                        // lb://{nameOfApplicationOnEurekaServer}
                        // On the Eureka server, this is registered as currency-exchange. So, if a request
                        // starts with currency-exchange, what we want to do is:
                        // - Talk to Eureka
                        // - Find the location of this service
                        // - Load balance between the instances
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                // Say we want to add a new url and re-direct it to currency-conversion-feign
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters( f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"
                        ))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
