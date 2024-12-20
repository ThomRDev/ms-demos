# FROM eclipse-temurin:17-jdk as builder
# WORKDIR /app
# COPY . .
# RUN ./mvnw clean package -DskipTests

# FROM eclipse-temurin:17-jre as layertools
# WORKDIR /application
# ARG JAR_FILE=target/*.jar
# COPY --from=builder /app/${JAR_FILE} application.jar
# RUN java -Djarmode=layertools -jar application.jar extract

# FROM eclipse-temurin:17-jre
# WORKDIR /application
# COPY --from=layertools application/dependencies/ ./
# COPY --from=layertools application/spring-boot-loader/ ./
# COPY --from=layertools application/snapshot-dependencies/ ./
# COPY --from=layertools application/application/ ./
# ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

# # docker build -t thomrdev/api-gateway .

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