# Currency Exchange Microservice

## URL
http://localhost:8000/currency-exchange/from/USD/to/INR

## API Gateway URL
http://localhost:8765/currency-exchange/from/USD/to/INR

## Response Structure
{
   "id":10001,
   "from":"USD",
   "to":"INR",
   "conversionMultiple":65.00,
   "environment":"8000 instance-id"
}

## Docker image
.\mvnw spring-boot:build-image -DskipTests

## Run Docker container
docker run -p 8000:8000 rafaellopez/currency-exchange-service:0.0.1-SNAPSHOT