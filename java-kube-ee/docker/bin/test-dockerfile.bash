#!/usr/bin/env bash

bash ./gradlew clean build
image_name="docker-java-ee-examples"
app_name="app"
docker build --rm=true --force-rm -f ./docker/src/Dockerfile --tag="$image_name" .
docker run -d -p 8080:8080 --rm --name "$app_name" "$image_name"
sleep 30
http :8080
docker rm -f -v "$app_name"
docker rmi -f "$image_name"
bash ./docker/bin/cleanup.bash
