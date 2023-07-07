FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD ./build/libs/user-service-0.0.1-SNAPSHOT-plain.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]
