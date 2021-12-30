FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]