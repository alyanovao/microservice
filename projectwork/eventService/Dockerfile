FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./eventService/target/*.jar
COPY ${JAR_FILE} eventService.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "eventService.jar"]