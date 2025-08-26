# compilacao
FROM maven:3.9.6-jdk-21-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/*.jar ./app
EXPOSE 8009
ENTRYPOINT ["java", "-jar", "/app.jar"]