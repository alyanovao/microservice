FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./deliveryService/target/*.jar
COPY ${JAR_FILE} deliveryService.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "deliveryService.jar"]