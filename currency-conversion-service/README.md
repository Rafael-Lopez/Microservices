# Currency Conversion Microservice

## URL
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

## API Gateway URL
http://localhost:8765/currency-conversion/from/EUR/to/INR/quantity/10
http://localhost:8765/currency-conversion-feign/from/EUR/to/INR/quantity/10

## Response Structure
{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65.00,
  "quantity": 10,
  "totalCalculatedAmount": 650.00,
  "environment": "8000 instance-id"
}

## Build Docker image
.\mvnw spring-boot:build-image -DskipTests

## Run Docker container
docker run -p 8100:8100 rafaellopez/currency-conversion-service:0.0.1-SNAPSHOT