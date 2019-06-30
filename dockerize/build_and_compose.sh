#!/usr/bin/env bash 

./sql-docker/build_docker.sh

./sb-docker/build_docker.sh

docker-compose up