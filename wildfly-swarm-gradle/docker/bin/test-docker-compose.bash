#!/usr/bin/env bash
set -x
docker-compose -f ./docker/src/docker-compose.yml build --force-rm --no-cache --pull
docker-compose -f ./docker/src/docker-compose.yml up -d
sleep 25
http :8080
docker-compose -f ./docker/src/docker-compose.yml down -v

bash ./docker/bin/cleanup.bash
