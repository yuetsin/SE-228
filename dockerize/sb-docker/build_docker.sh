#!/usr/bin/env bash 

cd ../../server

./mvnw clean package -DskipTests

mv server-0.1.0-SNAPSHOT.jar ../dockerize/sb-docker/app.jar

cd ../dockerize/sb-docker

docker build -t yuxiqian/bookie-server .