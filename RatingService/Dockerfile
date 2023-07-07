FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8082
ADD ./build/libs/RatingService-0.0.1-SNAPSHOT.jar rating-service.jar
ENTRYPOINT ["java","-jar","rating-service.jar"]

