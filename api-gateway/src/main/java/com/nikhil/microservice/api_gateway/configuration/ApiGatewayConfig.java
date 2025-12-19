package com.nikhil.microservice.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f ->
                                f.addRequestHeader("CustomHeader","My Header")
                                        .addRequestParameter("param","myParameter")
                        )
                        .uri("http://httpbin.org:80"))
                //This mapping converts
                //http://localhost:8765/CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/USD/to/INR
                //http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/11
                //TO
                // http://localhost:8765/currency-exchange/from/USD/to/INR
                // http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/11
                //same thing can be achieved from properties file.
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://CURRENCY-EXCHANGE-SERVICE/"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE/"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE/"))
                .build();
    }
}
