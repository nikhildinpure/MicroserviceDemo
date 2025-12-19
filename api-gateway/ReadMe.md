Provides crosscutting concerns:
1. Security
2. Monitoring/Metrics

Reactive approach built on top of Spring WebFlux

Features:
1. Match routes on any request attribute.
2. Define Predicates and Filters
3. Integrates with Spring Cloud Discovery Client 
4. Path Rewriting

Reactive, built on Spring WebFlux → Non-blocking, scalable for high loads.

Route Configuration
Either via application.properties/yml or Java Config.
Example in application.properties:
1. spring.cloud.gateway.routes[0].id=user-service
2. spring.cloud.gateway.routes[0].uri=lb://user-service
3. spring.cloud.gateway.routes[0].predicates[0]=Path=/users/**

lb://user-service → Load-balanced route using Eureka.

Filters
1. Pre-filters → Modify requests before hitting microservices
2. Post-filters → Modify responses before sending to client
3. Examples: Add auth token, logging, transform response JSON.

http://localhost:8765/
http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/USD/to/INR/quantity/5
http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR

There is problem with springboot 4.0.0 and spring-cloud.version 
Compatible version found as below 
springboot version - 
    <version>3.4.0</version>
Spring cloud version - 
    <spring-cloud.version>2024.0.0</spring-cloud.version>