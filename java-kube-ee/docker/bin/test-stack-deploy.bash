#!/usr/bin/env bash

bash ./gradlew clean build

stack_name="java-ee"

docker swarm init
docker service create --detach=false --name registry --publish 5000:5000 registry:2
docker-compose -f ./docker/src/stack-deploy.yml build --force-rm --no-cache --pull
docker-compose -f ./docker/src/stack-deploy.yml push
docker stack deploy --compose-file ./docker/src/stack-deploy.yml "$stack_name"

delay=1
# put apps in right order
for name in app; do
  service_name="${stack_name}_${name}"

  # info
  echo "waiting for $service_name bootstrap..."
  echo $(docker stack services --filter name="$service_name" --format="{{.Name}} {{.Replicas}}" "$stack_name")
  state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" "$stack_name")

  # scale down first...
  while [ "$state" != "0/0" ]; do
    docker service scale --detach=true "$service_name"=0
    state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" "$stack_name")
    sleep "$delay"
  done

  # scale up in right order
  while [ "$state" != "1/1" ]; do
    docker service scale --detach=false "$service_name"=1
    state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" "$stack_name")
    sleep "$delay"
  done
done

docker stack services "$stack_name"
sleep 30

http :8080

bash ./docker/bin/cleanup.bash
