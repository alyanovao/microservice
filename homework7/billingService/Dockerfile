FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=./billingService/target/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} billingService.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "billingService.jar"]