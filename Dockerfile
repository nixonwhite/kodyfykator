FROM amazoncorretto:17-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]
