FROM openjdk:21-jdk-slim

WORKDIR /app

COPY Backend-0.0.2.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]