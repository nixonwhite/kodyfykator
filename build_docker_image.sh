#!/bin/sh
./gradlew bootJar
docker build . -t nixonwhite/kodyfykator
./gradlew clean
docker push nixonwhite/kodyfykator