FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy

RUN apt-get update && apt-get upgrade -y && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

COPY --from=build /target/DistriCommerce-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
