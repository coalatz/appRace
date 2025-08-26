# Staging 1: Compilar a aplicação
FROM maven:3.9.6-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Staging 2: Criar a imagem final
FROM openjdk:21-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8009
ENTRYPOINT ["java", "-jar", "/app.jar"]