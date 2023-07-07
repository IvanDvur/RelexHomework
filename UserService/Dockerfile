FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD /build/libs/UserService-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]
