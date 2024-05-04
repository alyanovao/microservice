FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} customService.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "customService.jar"]