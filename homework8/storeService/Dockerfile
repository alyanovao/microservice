FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./storeService/target/*.jar
COPY ${JAR_FILE} storeService.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "storeService.jar"]