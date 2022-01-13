FROM openjdk:8-jdk-oraclelinux8
#FROM openjdk:8-jdk-slim
#RUN addgroup spring && adduser spring --ingroup spring
RUN groupadd spring && adduser spring -g spring
USER spring:spring
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kodyfykator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kodyfykator.jar"]