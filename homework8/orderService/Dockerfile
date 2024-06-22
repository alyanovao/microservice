FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./orderService/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} orderService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "orderService.jar"]