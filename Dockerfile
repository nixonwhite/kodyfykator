FROM amazoncorretto:17-alpine
RUN apk update && apk -U upgrade
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]
