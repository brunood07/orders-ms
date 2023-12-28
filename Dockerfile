FROM openjdk:17-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/orders-ms-0.0.1-SNAPSHOT*.jar
ADD --chown=spring:spring ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
