FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./notificationService/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} notificationService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "notificationService.jar"]