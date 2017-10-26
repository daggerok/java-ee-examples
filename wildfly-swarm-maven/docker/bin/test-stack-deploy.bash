#!/usr/bin/env bash

docker swarm init
docker service create --detach=false --name registry --publish 5000:5000 registry:2
#docker build -f ./docker/src/Dockerfile -t 127.0.0.1:5000/app .
#docker push 127.0.0.1:5000/app
docker-compose -f ./docker/src/stack-deploy.yml build --force-rm --no-cache --pull
docker-compose -f ./docker/src/stack-deploy.yml push
docker stack deploy --compose-file ./docker/src/stack-deploy.yml java-ee

sequence=1
for service_name in java-ee_app; do
  echo "waiting for $service_name bootstrap..."
  echo $(docker stack services --filter name="$service_name" --format="{{.Name}} {{.Replicas}}" java-ee)
  state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" java-ee)
  while [ "$state" != "$sequence/$sequence" ]; do
    sleep "$sequence"
    docker service scale --detach=true "$service_name"="$sequence"
    state=$(docker stack services --filter name="$service_name" --format="{{.Replicas}}" java-ee)
  done
  #sequence=$(expr "$sequence" + 1)
done

sleep 30

docker stack services java-ee

http :8080

bash docker/bin/cleanup.bash
