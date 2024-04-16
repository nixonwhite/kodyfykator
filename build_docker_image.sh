gradle bootJar
docker build . -t nixonwhite/kodyfykator
gradle clean
docker push nixonwhite/kodyfykator