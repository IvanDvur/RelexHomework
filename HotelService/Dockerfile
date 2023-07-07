FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8081
ADD ./HotelService/build/libs/HotelService-0.0.1-SNAPSHOT.jar hotel-service.jar
ENTRYPOINT ["java","-jar","hotel-service.jar"]

