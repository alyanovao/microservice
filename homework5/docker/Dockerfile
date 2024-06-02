FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./users-api/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} user-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-api.jar"]