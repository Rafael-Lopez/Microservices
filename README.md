# Microservices Course

1. Basic RESTful API project
   - restful-web-service

2. Basic Spring Cloud project
   - limits-service: sample Spring Cloud Client app connecting to spring-cloud-config-server
   - spring-cloud-config-server: sample Spring Cloud Config Server

The configuration for the Spring Cloud Config Server is located at https://github.com/Rafael-Lopez/Microservices-Config-Server

3. Currency Conversion Microservices
   - currency-exchange-service
   - currency-conversion-service
   - naming-server (Eureka Naming Server)
   - api-gateway (Spring Cloud Gateway)

Zipkin is the Distributed Tracing system we are using for this project. Lunch a Zipkin container by running 
"docker run -p 9411:9411 openzipkin/zipkin:2.23"

| Application  | Port |
| ------------- | ------------- |
| Limits Microservice  | 8080, 8081, ...  |
| Spring Cloud Config Server  | 8888  |
| Currency Exchange Microservice  | 8000, 8001, 8002, ..  |
| Currency Conversion Microservice  | 8100, 8101, 8102, ...  |
| Netflix Eureka Naming Server  | 8761  |
| API Gateway  | 8765  |
| Zipkin Distributed Tracing Server  | 9411  |
| RabbitMQ  | 15672  |
