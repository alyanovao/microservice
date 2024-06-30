FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./consumerService/target/*.jar
COPY ${JAR_FILE} consumerService.jar
EXPOSE 8101
ENTRYPOINT ["java", "-jar", "consumerService.jar"]