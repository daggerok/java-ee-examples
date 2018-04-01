#!/usr/bin/env bash

docker swarm init
docker service create --detach=false --name registry --publish 5000:5000 registry:2
#docker build -f ./docker/src/Dockerfile -t 127.0.0.1:5000/app .
#docker push 127.0.0.1:5000/app
docker-compose -f ./docker/src/stack-deploy.yml build --force-rm --no-cache --pull >/dev/null 2>&1
docker-compose -f ./docker/src/stack-deploy.yml push >/dev/null 2>&1

stack_name="wildfly-swarm-maven"
docker stack deploy --compose-file ./docker/src/stack-deploy.yml ${stack_name} >/dev/null 2>&1

for name in app; do
  service_name="${stack_name}_${name}"
  echo "waiting for $service_name bootstrap..."
  echo $(docker stack services --filter name="$service_name" --format="{{.Name}} {{.Replicas}}" ${stack_name})
  state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" ${stack_name})
  while [ "$state." != "1/1." ]; do
    sleep 1
    docker stack services "$stack_name"
    docker service scale --detach=false "$service_name=1" >/dev/null 2>&1
    state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" ${stack_name})
  done
done

sleep 30
docker stack services ${stack_name}

http :8080

bash docker/bin/cleanup.bash >/dev/null 2>&1
