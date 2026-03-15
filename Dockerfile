FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY src src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/orbita-0.0.1-SNAPSHOT.jar java-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "java-app.jar", "--spring.profiles.active=prod"]
