#!/usr/bin/env bash
docker-compose -f docker/src/docker-compose.yml down -v
docker-compose -f docker/src/stack-deploy.yml down -v
docker swarm leave --force
docker system prune -af --volumes
