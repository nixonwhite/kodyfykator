# Kodyfykator 2021

[![Java CI with Maven](https://github.com/nixonwhite/kodyfykator/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/nixonwhite/kodyfykator/actions/workflows/maven.yml)

Helper reference guide-codifier of the settlements of Ukraine after areas and regions merging.

Used Spring Boot. Embedded `Tomcat` instance runs at `8080` port

## Build && run
>$ mvn clean package -DskipTests

>$ java -jar target/*.jar

Also available at [hub.docker.com](https://hub.docker.com)

>$ docker run -p 8080:8080 -t nixonwhite/kodyfykator

## Usage

**`POST`** request: `http://localhost:8080/find/{name_of_the_settlement}`

**Result**: JSON array

>$ curl -X POST http://localhost:8080/find/settlement
> 
**`GET`** request: `http://localhost:8080/version`

**Result**: version of application

## Links

Official documents here: [mtu.gov.ua](https://mtu.gov.ua/content/kodifikator-administrativnoteritorialnih-odinic-ta-teritoriy-teritorialnih-gromad.html)
