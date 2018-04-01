#!/usr/bin/env bash

docker build --force-rm -f ./docker/src/Dockerfile -t docker-java-ee-examples .
docker run -d -p 8080:8080 --rm --name app docker-java-ee-examples >/dev/null 2>&1
sleep 30
http :8080
docker rm -f -v app >/dev/null 2>&1

bash ./docker/bin/cleanup.bash >/dev/null 2>&1
