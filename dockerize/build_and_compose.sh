#!/usr/bin/env bash 

cd sql-docker
./build_docker.sh

cd ../

cd sb-docker
./build_docker.sh

cd ../

docker push yuxiqian/bookie-mysql
docker push yuxiqian/bookie-server

docker-compose up