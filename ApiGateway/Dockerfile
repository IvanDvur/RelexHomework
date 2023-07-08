FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8083
ADD ./ApiGateway/build/libs/ApiGateway-0.0.1-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]