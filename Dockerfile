FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]