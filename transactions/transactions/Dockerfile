
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN chown spring:spring app.jar && chmod 500 app.jar

USER spring

VOLUME /tmp

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar"]