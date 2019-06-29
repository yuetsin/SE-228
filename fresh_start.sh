#!/usr/bin/env bash 

cd ./web/bookie

if npm run build; then
    echo "build run complete"
else
    echo "build run complete with error"
fi

cd ../../

cp -v ./web/bookie/dist/index.html ./server/src/main/resources/templates/index.html

cp -R -P ./web/bookie/dist/js ./server/src/main/resources/static

cd ./server
./mvnw spring-boot:run