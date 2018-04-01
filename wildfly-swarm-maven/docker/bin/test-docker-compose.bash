#!/usr/bin/env bash

docker-compose -f ./docker/src/docker-compose.yml build --force-rm --no-cache --pull >/dev/null 2>&1
docker-compose -f ./docker/src/docker-compose.yml up -d >/dev/null 2>&1
sleep 30
http :8080
docker-compose -f ./docker/src/docker-compose.yml down -v >/dev/null 2>&1

bash ./docker/bin/cleanup.bash >/dev/null 2>&1
