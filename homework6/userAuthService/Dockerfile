FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./userAuthService/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} userAuth.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "userAuth.jar"]