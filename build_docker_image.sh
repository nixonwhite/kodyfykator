mvn clean package -DskipTests
docker build . -t nixonwhite/kodyfykator
#docker push nixonwhite/kodyfykator
mvn clean
