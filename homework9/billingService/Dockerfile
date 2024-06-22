FROM bellsoft/liberica-openjdk-alpine:21 AS build
ARG JAR_FILE=./billingService/target/*.jar
COPY ${JAR_FILE} billingService.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "billingService.jar"]