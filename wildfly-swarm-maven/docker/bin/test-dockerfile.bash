#!/usr/bin/env bash
set -x
docker build --force-rm -f ./docker/src/Dockerfile -t docker-java-ee-example .
docker run -d -p 8080:8080 --rm --name app docker-java-ee-example
sleep 20
http :8080
docker stop app

bash ./docker/bin/cleanup.bash
