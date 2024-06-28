FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./visualizationService/target/*.jar
COPY ${JAR_FILE} visualizationService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "visualizationService.jar"]