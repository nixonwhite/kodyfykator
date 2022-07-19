mvn clean package -DskipTests
docker build . -t nixonwhite/kodyfykator
mvn clean
docker push nixonwhite/kodyfykator

