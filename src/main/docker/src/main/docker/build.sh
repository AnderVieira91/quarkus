#!/bin/bash
mvn package -Pnative -Dnative-image.docker-build=true
docker-compose up --build

docker build -f Dockerfile.native -t projeto-quarkus:latest .
