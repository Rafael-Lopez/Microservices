# Netflix Eureka Naming Server

## URL
http://localhost:8761/

## Docker image
.\mvnw spring-boot:build-image -DskipTests

## Run Docker container
docker run -p 8761:8761 rafaellopez/naming-server:0.0.1-SNAPSHOT