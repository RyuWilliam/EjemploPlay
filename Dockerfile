# Etapa 1: Build con Gradle y JDK 24
FROM gradle:8.14.3-jdk24 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

# Etapa 2: Runtime con Eclipse Temurin JDK 24
FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
