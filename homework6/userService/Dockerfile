FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./userService/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} userService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "userService.jar"]