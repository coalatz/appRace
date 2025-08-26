FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21 -y 
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8009

COPY --from=build /target/Run-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar"]