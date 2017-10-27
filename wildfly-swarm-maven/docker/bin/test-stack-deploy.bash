#!/usr/bin/env bash

docker swarm init
docker service create --detach=false --name registry --publish 5000:5000 registry:2
#docker build -f ./docker/src/Dockerfile -t 127.0.0.1:5000/app .
#docker push 127.0.0.1:5000/app
docker-compose -f ./docker/src/stack-deploy.yml build --force-rm --no-cache --pull
docker-compose -f ./docker/src/stack-deploy.yml push

stack_name="java-ee"
docker stack deploy --compose-file ./docker/src/stack-deploy.yml ${stack_name}

for name in app; do
  service_name="${stack_name}_${name}"
  echo "waiting for $service_name bootstrap..."
  echo $(docker stack services --filter name="$service_name" --format="{{.Name}} {{.Replicas}}" ${stack_name})
  state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" ${stack_name})
  while [ "$state." != "1/1." ]; do
    sleep 1
    docker stack services "$stack_name"
    docker service scale --detach=false "$service_name=1"
    state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" ${stack_name})
  done
done

sleep 30
docker stack services ${stack_name}

http :8080

bash docker/bin/cleanup.bash
