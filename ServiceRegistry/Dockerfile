FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8083
ADD ./ServiceRegistry/build/libs/ServiceRegistry-0.0.1-SNAPSHOT.jar service-registry.jar
ENTRYPOINT ["java","-jar","service-registry.jar"]