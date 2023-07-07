FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8081
ADD ./build/libs/hotel-service-0.0.1-SNAPSHOT-plain.jar hotel-service.jar
ENTRYPOINT ["java","-jar","hotel-service.jar"]

