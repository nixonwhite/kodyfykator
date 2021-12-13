# Kodyfykator 2021

Helper reference guide-codifier of the settlements of Ukraine after areas and regions merging.

Used Spring Boot. Embedded Tomcat instance runs at 8080 port

$ mvn clean package 
$ java -jar target/Kodyfykator-1.0-SNAPSHOT.jar

Also available at hub.docker.com

$ docker run -p 8080:8080 -t nixonwhite/kodyfykator

POST request: http://localhost:8080/find/{name_of_the_settlement}

Result: JSON array 

GET request: http://localhost:8080/version

Result: version of API used
