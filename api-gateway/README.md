# API Gateway

- VERSION 1 -
http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/EUR/to/INR/quantity/10
http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/EUR/to/INR/quantity/10

The CURRENCY-CONVERSION and CURRENCY-EXCHANGE parts are coming from the Eureka server.
Go to http://localhost:8761 to see this information.

- VERSION 2 -
If spring.cloud.gateway.discovery.locator.lower-case-service-id=true in application properties, then

http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR
http://localhost:8765/currency-conversion/currency-conversion/from/EUR/to/INR/quantity/10
http://localhost:8765/currency-conversion/currency-conversion-feign/from/EUR/to/INR/quantity/10

- VERSION 3 -
We declare routes and comment out:
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
in application.properties

http://localhost:8765/currency-exchange/from/USD/to/INR
http://localhost:8765/currency-conversion/from/EUR/to/INR/quantity/10
http://localhost:8765/currency-conversion-feign/from/EUR/to/INR/quantity/10
