#FROM openjdk:11-oracle
FROM amazoncorretto:11-al2-jdk
#RUN groupadd spring && adduser spring -g spring
#USER spring:spring
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]
