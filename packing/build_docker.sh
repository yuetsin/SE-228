#!/usr/bin/env bash 

docker image build -t yuxiqian/bookie .

docker push yuxiqian/bookie:latest