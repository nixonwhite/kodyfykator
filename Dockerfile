FROM bellsoft/liberica-runtime-container:jre-24-glibc
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]