#!/usr/bin/env bash

minikube start --cpus=4 --memory=4096
sleep 10
eval $(minikube docker-env)
docker run -d --rm --name registry -p 5000:5000 registry:2
bash gradlew clean build
docker build -t 127.0.0.1:5000/app -f ./docker/src/Dockerfile .
docker push 127.0.0.1:5000/app

kubectl apply -f ./docker/k8s --validate=false
sleep 20
kubectl get svc -o wide
http $(minikube service app --url)

kubectl get pods -o wide

kubectl autoscale deployment app --min=2 --max=3
sleep 30
kubectl get pods -o wide

#kubectl scale deployment app --replicas=3
#sleep 30
#kubectl get pods -o wide
